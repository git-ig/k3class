variable "project_id" {
  description = "The GCP project ID."
  type        = string
}

variable "network_name" {
  description = "The name of the VPC network."
  type        = string
}

variable "public_subnet_cidr" {
  description = "The CIDR block of the public subnet."
  type        = string
}

variable "private_subnet_cidr" {
  description = "The CIDR block of the private subnet."
  type        = string
}
