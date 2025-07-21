# k3s Control Plane outputs
output "k3s_control_plane_public_ip" {
  description = "The public IP address of the k3s control plane"
  value       = google_compute_instance.k3s_control_plane.network_interface[0].access_config[0].nat_ip
}

output "k3s_control_plane_private_ip" {
  description = "The private IP address of the k3s control plane"
  value       = google_compute_instance.k3s_control_plane.network_interface[0].network_ip
}

# k3s Worker Nodes outputs
output "k3s_worker_private_ips" {
  description = "The private IP addresses of the k3s worker nodes"
  value       = google_compute_instance.k3s_worker[*].network_interface[0].network_ip
}

output "k3s_worker_names" {
  description = "The names of the k3s worker nodes"
  value       = google_compute_instance.k3s_worker[*].name
}

# Legacy compatibility outputs for smooth transition
output "bastion_public_ip" {
  description = "The public IP address (using control plane for compatibility)"
  value       = google_compute_instance.k3s_control_plane.network_interface[0].access_config[0].nat_ip
}

output "frontend_private_ip" {
  description = "The private IP address of worker-1 (frontend)"
  value       = google_compute_instance.k3s_worker[0].network_interface[0].network_ip
}

output "backend_private_ip" {
  description = "The private IP address of worker-2 (backend)"
  value       = google_compute_instance.k3s_worker[1].network_interface[0].network_ip
}

output "database_private_ip" {
  description = "The private IP address of worker-3 (database)"
  value       = google_compute_instance.k3s_worker[2].network_interface[0].network_ip
}

output "monitoring_private_ip" {
  description = "The private IP address of control plane (monitoring)"
  value       = google_compute_instance.k3s_control_plane.network_interface[0].network_ip
}
