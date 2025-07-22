variable "project_id" {
  description = "The GCP project ID."
  type        = string
}

variable "region" {
  description = "The GCP region to deploy resources in."
  type        = string
}

variable "zone" {
  description = "The GCP zone to deploy resources in."
  type        = string
}

variable "vm_image" {
  description = "The OS image for all VMs."
  type        = string
  default     = "ubuntu-2404-lts"
}

variable "ssh_user" {
  description = "The username for SSH access."
  type        = string
}

variable "ssh_public_key_content" {
  description = "The content of the public SSH key."
  type        = string
}

variable "bucket_name" {
  description = "The name of the GCS bucket for Terraform state."
  type        = string
}

variable "service_account_email" {
  description = "Email of the service account for GCS bucket access"
  type        = string
  default     = ""
}

variable "cloudflare_api_token" {
  description = "Cloudflare API token for DNS management"
  type        = string
  sensitive   = true
}

variable "cloudflare_zone_id" {
  description = "Cloudflare zone ID for dock.ink domain"
  type        = string
}
