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

  # Service account for GCS access - CRITICAL FIX
  service_account {
    email  = var.service_account_email
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  # Improved startup script with error handling and logging
  metadata_startup_script = <<-EOF
#!/bin/bash
set -e

# Setup logging
exec > >(tee -a /var/log/k3s-startup.log) 2>&1
echo "Starting k3s installation at $(date)"

# Install k3s on control plane
echo "Installing k3s..."
curl -sfL https://get.k3s.io | sh -s - --write-kubeconfig-mode 644

# Wait for k3s to start
echo "Waiting for k3s service..."
while ! systemctl is-active --quiet k3s; do
  sleep 5
done

# Wait for token to be available
echo "Getting node token..."
while [ ! -f /var/lib/rancher/k3s/server/node-token ]; do
  sleep 5
done

TOKEN=$(sudo cat /var/lib/rancher/k3s/server/node-token)

# Get public IP from metadata server and update kubeconfig before uploading
echo "Updating kubeconfig with the public IP..."
PUBLIC_IP=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip)
sed -i "s/127.0.0.1/$PUBLIC_IP/g" /etc/rancher/k3s/k3s.yaml

# Upload kubeconfig to GCS
echo "Uploading kubeconfig..."
gcloud storage cp /etc/rancher/k3s/k3s.yaml gs://${var.bucket_name}/k3s-kubeconfig

# Upload token to GCS for workers
echo "Uploading token..."
echo "$TOKEN" | gcloud storage cp - gs://${var.bucket_name}/k3s-token

echo "k3s installation completed at $(date)"
EOF

  network_interface {
    network    = var.network_name
    subnetwork = var.public_subnet_name
    access_config {
      // Ephemeral public IP
    }
  }
}

# k3s Worker Nodes - private IP only, no SSH keys
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

  # Service account for GCS access - CRITICAL FIX
  service_account {
    email  = var.service_account_email
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
  }

  # No SSH keys on workers for security
  metadata = {}

  # Improved startup script with consistent gcloud commands
  metadata_startup_script = <<-EOF
#!/bin/bash
set -e

# Setup logging
exec > >(tee -a /var/log/k3s-worker-startup.log) 2>&1
echo "Starting k3s worker installation at $(date)"

# Wait for token from control plane
echo "Waiting for token from control plane..."
while ! gcloud storage cp gs://${var.bucket_name}/k3s-token /tmp/k3s-token; do
  sleep 10
done

TOKEN=$(cat /tmp/k3s-token)

# Install k3s as worker
echo "Joining k3s cluster..."
curl -sfL https://get.k3s.io | K3S_URL=https://${google_compute_instance.k3s_control_plane.network_interface[0].network_ip}:6443 K3S_TOKEN=$TOKEN sh -

echo "k3s worker installation completed at $(date)"
EOF

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
    # No access_config - private IP only
  }

  depends_on = [google_compute_instance.k3s_control_plane]
}
