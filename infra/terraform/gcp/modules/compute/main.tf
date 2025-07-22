# k3s Control Plane - public IP for external access
resource "google_compute_instance" "k3s_control_plane" {
  name         = "k3s-control-plane"
  machine_type = "e2-medium"
  zone         = var.zone
  tags         = ["k3s-control-plane", "http-server", "https-server"]

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 20
      type  = "pd-standard"
    }
  }

  service_account {
    email  = var.service_account_email
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  # metadata_startup_script for k3s-control-plane
      # metadata_startup_script for k3s-control-plane
  metadata_startup_script = <<-EOF
#!/bin-bash
set -ex # Enable debug mode

# Log everything
exec > >(tee -a /var/log/startup-script.log) 2>&1

echo "--- K3s Control Plane Setup Started ---"
date

# STEP 1: Install k3s
# This creates the default /etc/rancher/k3s/k3s.yaml owned by root
curl -sfL https://get.k3s.io | sh -s - --write-kubeconfig-mode 644

# STEP 2: Wait for k3s service and node token
while ! systemctl is-active --quiet k3s || [ ! -f /var/lib/rancher/k3s/server/node-token ]; do
  echo "Waiting for k3s service and node-token..."
  sleep 5
done
TOKEN=$(cat /var/lib/rancher/k3s/server/node-token)

# --- STEP 3: PREPARE A WORKING KUBECONFIG (YOUR SUGGESTION) ---
# Copy the root-owned kubeconfig to a temporary, accessible location
echo "--- Preparing a working kubeconfig ---"
cp /etc/rancher/k3s/k3s.yaml /tmp/k3s-config-temp.yaml
# Set the KUBECONFIG environment variable for all subsequent kubectl commands
export KUBECONFIG=/tmp/k3s-config-temp.yaml

# --- STEP 4: CREATE A PORTABLE/FLATTENED KUBECONFIG ---
# Now, use the exported KUBECONFIG to create the flattened version
echo "--- Creating a portable kubeconfig ---"
kubectl config view --flatten > /tmp/kubeconfig-portable.yaml

# STEP 5: Upload artifacts to GCS
echo "--- Uploading artifacts to GCS ---"
# Upload the new, portable kubeconfig
gcloud storage cp /tmp/kubeconfig-portable.yaml gs://${var.bucket_name}/k3s-kubeconfig
# Upload the token for workers
echo "$TOKEN" | gcloud storage cp - gs://${var.bucket_name}/k3s-token

# Clean up temporary files
rm /tmp/k3s-config-temp.yaml /tmp/kubeconfig-portable.yaml

echo "--- K3s Control Plane Setup Finished ---"
date
EOF

  network_interface {
    network    = var.network_name
    subnetwork = var.public_subnet_name
    access_config {}
  }
}

# k3s Worker Nodes (with debug mode)
resource "google_compute_instance" "k3s_worker" {
  count        = 3
  name         = "k3s-worker-${count.index + 1}"
  machine_type = "e2-small"
  zone         = var.zone
  tags         = ["k3s-worker"]

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 20
      type  = "pd-standard"
    }
  }

  service_account {
    email  = var.service_account_email
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }

  metadata = {}

  metadata_startup_script = <<-EOF
#!/bin/bash
set -ex # --- ENABLE DEBUG MODE ---

# Setup logging
exec > >(tee -a /var/log/k3s-worker-startup.log) 2>&1
echo "--- WORKER SCRIPT START ---"
date

export DEBIAN_FRONTEND=noninteractive

# Install Google Cloud CLI
echo "--- WORKER STEP 1: INSTALLING GCLOUD ---"
apt-get update -y
apt-get install -y apt-transport-https ca-certificates gnupg curl
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /usr/share/keyrings/cloud.google.gpg
echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | tee /etc/apt/sources.list.d/google-cloud-sdk.list > /dev/null
apt-get update -y
apt-get install -y google-cloud-cli
echo "--- WORKER STEP 1 COMPLETE ---"

# Wait for token
echo "--- WORKER STEP 2: WAITING FOR TOKEN ---"
while ! gcloud storage cp gs://${var.bucket_name}/k3s-token /tmp/k3s-token; do
  echo "Token not available in GCS, retrying in 10s..."
  sleep 10
done
echo "--- WORKER STEP 2 COMPLETE ---"
TOKEN=$(cat /tmp/k3s-token)

# Install k3s worker
echo "--- WORKER STEP 3: JOINING CLUSTER ---"
curl -sfL https://get.k3s.io | K3S_URL=https://${google_compute_instance.k3s_control_plane.network_interface[0].network_ip}:6443 K3S_TOKEN=$TOKEN sh -
echo "--- WORKER STEP 3 COMPLETE ---"

echo "--- WORKER SCRIPT FINISHED SUCCESSFULLY ---"
date
EOF

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
  }

  depends_on = [google_compute_instance.k3s_control_plane]
}