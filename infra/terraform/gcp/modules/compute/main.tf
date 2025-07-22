# k3s Control Plane - public IP for external access
resource "google_compute_instance" "k3s_control_plane" {
  name         = "k3s-control-plane"
  machine_type = "e2-small"
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

  metadata_startup_script = <<-EOF
#!/bin/bash
set -ex # --- ENABLE DEBUG MODE (echo all commands) ---

# Setup logging
exec > >(tee -a /var/log/k3s-startup.log) 2>&1
echo "--- SCRIPT START ---"
date

# Set non-interactive mode for apt
export DEBIAN_FRONTEND=noninteractive

# Install Google Cloud CLI
echo "--- STEP 1: INSTALLING GCLOUD ---"
apt-get update -y
apt-get install -y apt-transport-https ca-certificates gnupg curl
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /usr/share/keyrings/cloud.google.gpg
echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | tee /etc/apt/sources.list.d/google-cloud-sdk.list > /dev/null
apt-get update -y
apt-get install -y google-cloud-cli
echo "--- STEP 1 COMPLETE ---"
gcloud --version

# Install k3s
echo "--- STEP 2: INSTALLING K3S ---"
curl -sfL https://get.k3s.io | sh -s - --write-kubeconfig-mode 644
echo "--- STEP 2 COMPLETE ---"

# Wait for k3s service
echo "--- STEP 3: WAITING FOR K3S SERVICE ---"
while ! systemctl is-active --quiet k3s; do
  echo "k3s service is not active yet, waiting 5s..."
  sleep 5
done
echo "--- STEP 3 COMPLETE ---"

# Wait for node-token
echo "--- STEP 4: WAITING FOR NODE TOKEN ---"
while [ ! -f /var/lib/rancher/k3s/server/node-token ]; do
  echo "node-token not found yet, waiting 5s..."
  sleep 5
done
echo "--- STEP 4 COMPLETE ---"
TOKEN=$(cat /var/lib/rancher/k3s/server/node-token)

# Update kubeconfig
echo "--- STEP 5: UPDATING KUBECONFIG ---"
PUBLIC_IP=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip)
sed -i "s/127.0.0.1/$PUBLIC_IP/g" /etc/rancher/k3s/k3s.yaml
echo "--- STEP 5 COMPLETE ---"

# Upload to GCS
echo "--- STEP 6: UPLOADING TO GCS ---"
echo "Attempting to upload kubeconfig to gs://${var.bucket_name}/k3s-kubeconfig"
gcloud storage cp /etc/rancher/k3s/k3s.yaml gs://${var.bucket_name}/k3s-kubeconfig
echo "Kubeconfig uploaded."

echo "Attempting to upload token to gs://${var.bucket_name}/k3s-token"
echo "$TOKEN" | gcloud storage cp - gs://${var.bucket_name}/k3s-token
echo "Token uploaded."
echo "--- STEP 6 COMPLETE ---"

echo "--- SCRIPT FINISHED SUCCESSFULLY ---"
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