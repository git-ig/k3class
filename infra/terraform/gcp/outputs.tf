# k3s cluster outputs
output "k3s_control_plane_public_ip" {
  description = "The public IP address of the k3s control plane"
  value       = module.compute.k3s_control_plane_public_ip
}

output "k3s_control_plane_private_ip" {
  description = "The private IP address of the k3s control plane"
  value       = module.compute.k3s_control_plane_private_ip
}

output "k3s_worker_private_ips" {
  description = "The private IP addresses of the k3s worker nodes"
  value       = module.compute.k3s_worker_private_ips
}

# Legacy compatibility outputs
output "bastion_public_ip" {
  description = "The public IP address (control plane for compatibility)"
  value       = module.compute.k3s_control_plane_public_ip
}

output "frontend_private_ip" {
  description = "The private IP address of worker-1 (frontend)"
  value       = module.compute.k3s_worker_private_ips[0]
}

output "backend_private_ip" {
  description = "The private IP address of worker-2 (backend)"
  value       = module.compute.k3s_worker_private_ips[1]
}

output "database_private_ip" {
  description = "The private IP address of worker-3 (database)"
  value       = module.compute.k3s_worker_private_ips[2]
}

output "monitoring_private_ip" {
  description = "The private IP address of control plane (monitoring)"
  value       = module.compute.k3s_control_plane_private_ip
}

# Ansible inventory for k3s cluster
output "ansible_inventory" {
  description = "YAML inventory for Ansible k3s setup"
  sensitive   = true

  value = <<-YAML
	---
	all:
	  vars:
	    ansible_user: ${var.ssh_user}
	    ansible_ssh_private_key_file: "~/.ssh/id_rsa"
	    ansible_ssh_common_args: "-o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null"

	  children:
	    k3s_control_plane:
	      hosts:
	        control_plane:
	          ansible_host: ${module.compute.k3s_control_plane_public_ip}
	          private_ip: ${module.compute.k3s_control_plane_private_ip}
	          k3s_role: "control-plane"

	    k3s_workers:
	      hosts:
	        worker_1:
	          ansible_host: ${module.compute.k3s_worker_private_ips[0]}
	          ansible_ssh_common_args: "-o ProxyCommand='ssh -W %h:%p -q ${var.ssh_user}@${module.compute.k3s_control_plane_public_ip}'"
	          k3s_role: "worker"
	          node_labels: "app=frontend"
	        worker_2:
	          ansible_host: ${module.compute.k3s_worker_private_ips[1]}
	          ansible_ssh_common_args: "-o ProxyCommand='ssh -W %h:%p -q ${var.ssh_user}@${module.compute.k3s_control_plane_public_ip}'"
	          k3s_role: "worker"
	          node_labels: "app=backend"
	        worker_3:
	          ansible_host: ${module.compute.k3s_worker_private_ips[2]}
	          ansible_ssh_common_args: "-o ProxyCommand='ssh -W %h:%p -q ${var.ssh_user}@${module.compute.k3s_control_plane_public_ip}'"
	          k3s_role: "worker"
	          node_labels: "app=database"
	YAML
}
