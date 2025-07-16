output "bastion_public_ip" {
  description = "The public IP address of the bastion host."
  value       = google_compute_address.bastion_static_ip.address
}

output "frontend_private_ip" {
  description = "The private IP address of the frontend instance."
  value       = google_compute_instance.frontend.network_interface[0].network_ip
}

output "backend_private_ip" {
  description = "The private IP address of the backend instance."
  value       = google_compute_instance.backend.network_interface[0].network_ip
}

output "database_private_ip" {
  description = "The private IP address of the database instance."
  value       = google_compute_instance.database.network_interface[0].network_ip
}

output "monitoring_private_ip" {
  description = "The private IP address of the monitoring instance."
  value       = google_compute_instance.monitoring.network_interface[0].network_ip
}
