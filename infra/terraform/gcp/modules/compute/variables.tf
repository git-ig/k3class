variable "project_id" {
  description = "The GCP project ID."
  type        = string
}

variable "zone" {
  description = "The GCP zone to deploy resources in."
  type        = string
}

variable "public_subnet_name" {
  description = "The name of the public subnet."
  type        = string
}

variable "private_subnet_name" {
  description = "The name of the private subnet."
  type        = string
}

variable "network_name" {
  description = "The name of the VPC network."
  type        = string
}

variable "vm_image" {
  description = "The image to use for the virtual machines."
  type        = string
}

variable "ssh_user" {
  description = "The username for SSH access."
  type        = string
}

variable "ssh_public_key_content" {
  description = "The content of the public SSH key."
  type        = string
  sensitive   = true
}

variable "bucket_name" {
  description = "The GCS bucket name for storing k3s kubeconfig and tokens."
  type        = string
}

variable "service_account_email" {
  description = "Service account email for VM instances"
  type        = string
}