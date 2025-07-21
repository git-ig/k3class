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

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"  # SSH только на control plane
  }

  metadata_startup_script = <<-EOF
  #!/bin/bash
  # Install k3s on control plane
  curl -sfL https://get.k3s.io | sh -s - --write-kubeconfig-mode 644
  
  # Wait for token to be available
  sleep 30
  TOKEN=$(sudo cat /var/lib/rancher/k3s/server/node-token)
  
  # Upload kubeconfig to GCS
  gcloud storage cp /etc/rancher/k3s/k3s.yaml gs://${var.bucket_name}/k3s-kubeconfig
  
  # Upload token to GCS for workers to use
  echo $TOKEN | gcloud storage cp - gs://${var.bucket_name}/k3s-token
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

  # No SSH keys on workers for security
  metadata = {}

  metadata_startup_script = <<-EOF
  #!/bin/bash
  # Poll for token from GCS
  until gsutil cp gs://${var.bucket_name}/k3s-token /tmp/k3s-token; do
    sleep 10
  done
  TOKEN=$(cat /tmp/k3s-token)
  
  # Install k3s as worker
  curl -sfL https://get.k3s.io | K3S_URL=<https://${google_compute_instance.k3s_control_plane.network_interface>[0].network_ip}:6443


EOF

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
    # No access_config - private IP only
  }

  depends_on = [google_compute_instance.k3s_control_plane]
}
