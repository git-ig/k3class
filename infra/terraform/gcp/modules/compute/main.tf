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
      metadata_startup_script = <<-EOF
#!/bin/bash
set -ex
# Log everything to a file and to the serial console
exec > >(tee -a /var/log/startup-script.log) 2>&1

echo "--- K3s Control-Plane Setup Started ---"

# Get the VM public IP from GCP metadata
PUBLIC_IP=$(curl -s -H "Metadata-Flavor: Google" \
  "http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip")

###############################################################################
# Step 1 ─ Install k3s with a TLS SAN for the public IP
#   --tls-san <IP> forces k3s to put that address into the certificate SAN list
###############################################################################
curl -sfL https://get.k3s.io \
  | INSTALL_K3S_EXEC="--tls-san $${PUBLIC_IP}" \
    sh -s - --write-kubeconfig-mode 644   # kubeconfig readable by all users

###############################################################################
# Step 2 ─ Wait until k3s is up and the node-token file exists
###############################################################################
while ! systemctl is-active --quiet k3s \
      || [ ! -f /var/lib/rancher/k3s/server/node-token ]; do
  echo "Waiting for k3s service and node token..."
  sleep 5
done
TOKEN=$(cat /var/lib/rancher/k3s/server/node-token)

###############################################################################
# Step 3 ─ Patch the kubeconfig to use the public IP, then flatten it
###############################################################################
# Replace default server endpoint with the external IP
KUBECONFIG=/etc/rancher/k3s/k3s.yaml \
  kubectl config set-cluster default --server="https://$${PUBLIC_IP}:6443"

# Flatten embeds certificates/keys → makes the kubeconfig portable
KUBECONFIG=/etc/rancher/k3s/k3s.yaml \
  kubectl config view --flatten > /tmp/kubeconfig-portable.yaml

###############################################################################
# Step 4 ─ Upload kubeconfig and node token to GCS for the CI job
###############################################################################
gcloud storage cp /tmp/kubeconfig-portable.yaml gs://${var.bucket_name}/k3s-kubeconfig
echo "$TOKEN" | gcloud storage cp - gs://${var.bucket_name}/k3s-token

echo "--- K3s Control-Plane Setup Finished ---"
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

echo "--- WORKER STEP 3: JOINING CLUSTER ---"
SHORT_HOST="k3s-worker-${count.index + 1}"

curl -sfL https://get.k3s.io | \
  K3S_URL=https://${google_compute_instance.k3s_control_plane.network_interface[0].network_ip}:6443 \
  K3S_TOKEN=$TOKEN \
  sh -s - --node-name=$${SHORT_HOST}   # double $$
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