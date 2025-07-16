output "bastion_public_ip" {
  description = "The public IP address of the bastion host."
  value       = module.compute.bastion_public_ip
}

output "frontend_private_ip" {
  description = "The private IP address of the frontend instance."
  value       = module.compute.frontend_private_ip
}

output "backend_private_ip" {
  description = "The private IP address of the backend instance."
  value       = module.compute.backend_private_ip
}

output "database_private_ip" {
  description = "The private IP address of the database instance."
  value       = module.compute.database_private_ip
}

output "monitoring_private_ip" {
  description = "The private IP address of the monitoring instance."
  value       = module.compute.monitoring_private_ip
}

output "ansible_inventory" {
  description = "YAML inventory for Ansible"
  sensitive   = true

  value = <<-YAML
	---
	all:
	  vars:
	    ansible_user: ${var.ssh_user}
	    ansible_ssh_private_key_file: "~/.ssh/id_rsa"
	    ansible_ssh_common_args: |
	      -o ProxyCommand="ssh -W %h:%p -q ${var.ssh_user}@${module.compute.bastion_public_ip}"
	      -o StrictHostKeyChecking=no
	      -o UserKnownHostsFile=/dev/null

	  children:
	    bastion_group:
	      hosts:
	        bastion_host:
	          ansible_host: ${module.compute.bastion_public_ip}

	    private_instances:
	      children:
	        frontend_group:
	          hosts:
	            frontend_host:
	              ansible_host: ${module.compute.frontend_private_ip}

	        backend_group:
	          hosts:
	            backend_host:
	              ansible_host: ${module.compute.backend_private_ip}

	        database_group:
	          hosts:
	            database_host:
	              ansible_host: ${module.compute.database_private_ip}

	        monitoring_group:
	          hosts:
	            monitoring_host:
	              ansible_host: ${module.compute.monitoring_private_ip}
	YAML
}
