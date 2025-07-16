resource "google_compute_address" "bastion_static_ip" {
  name = "bastion-static-ip"
}

resource "google_compute_instance" "bastion" {
  name         = "bastion-host"
  machine_type = "e2-micro"
  zone         = var.zone
  tags         = ["bastion"]

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 10
      type  = "pd-standard"
    }
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  network_interface {
    network    = var.network_name
    subnetwork = var.public_subnet_name
    access_config {
      nat_ip = google_compute_address.bastion_static_ip.address
    }
  }
}

resource "google_compute_instance" "frontend" {
  name         = "frontend-instance"
  machine_type = "e2-micro"
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 10
      type  = "pd-standard"
    }
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
  }
}

resource "google_compute_instance" "backend" {
  name         = "backend-instance"
  machine_type = "e2-micro"
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 10
      type  = "pd-standard"
    }
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
  }
}

resource "google_compute_instance" "database" {
  name         = "database-instance"
  machine_type = "e2-micro"
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 10
      type  = "pd-standard"
    }
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
  }
}

resource "google_compute_instance" "monitoring" {
  name         = "monitoring-instance"
  machine_type = "e2-micro"
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.vm_image
      size  = 10
      type  = "pd-standard"
    }
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${var.ssh_public_key_content}"
  }

  network_interface {
    network    = var.network_name
    subnetwork = var.private_subnet_name
  }
}
