# SSH access to k3s control plane
resource "google_compute_firewall" "allow_ssh_to_control_plane" {
  name    = "allow-ssh-to-control-plane"
  network = var.network_name

  allow {
    protocol = "tcp"
    ports    = ["22"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["k3s-control-plane"]
}

# Internal traffic between subnets
resource "google_compute_firewall" "allow_internal" {
  name    = "allow-internal"
  network = var.network_name

  allow {
    protocol = "all"
  }

  source_ranges = [
    var.public_subnet_cidr,
    var.private_subnet_cidr
  ]
}

# k3s API server access
resource "google_compute_firewall" "allow_k3s_api" {
  name    = "allow-k3s-api"
  network = var.network_name

  allow {
    protocol = "tcp"
    ports    = ["6443"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["k3s-control-plane"]
}

# HTTP/HTTPS traffic for applications
resource "google_compute_firewall" "allow_http_https" {
  name    = "allow-http-https"
  network = var.network_name

  allow {
    protocol = "tcp"
    ports    = ["80", "443"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["k3s-control-plane"]
}

# k3s internal communication
resource "google_compute_firewall" "allow_k3s_internal" {
  name    = "allow-k3s-internal"
  network = var.network_name

  allow {
    protocol = "tcp"
    ports    = ["10250", "2379-2380"]
  }

  allow {
    protocol = "udp"
    ports    = ["8472"]
  }

  source_ranges = [
    var.public_subnet_cidr,
    var.private_subnet_cidr
  ]

  target_tags = ["k3s-control-plane", "k3s-worker"]
}

# NodePort services range
# resource "google_compute_firewall" "allow_nodeport" {
#   name    = "allow-nodeport"
#   network = var.network_name

#   allow {
#     protocol = "tcp"
#     ports    = ["30000-32767"]
#   }

#   source_ranges = [
#     var.public_subnet_cidr,
#     var.private_subnet_cidr
#   ]

#   target_tags = ["k3s-control-plane", "k3s-worker"]
# }
