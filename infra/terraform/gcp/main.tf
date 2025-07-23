terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 4.0"
    }
    cloudflare = {
      source  = "cloudflare/cloudflare"
      version = "~> 4.0"
    }
    random = {
      source  = "hashicorp/random"
      version = "~> 3.5"
    }
  }
  backend "gcs" {
    bucket = var.bucket_name
    prefix = "terraform/state"
  }
}

# Configure Google Cloud Provider
provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

# Create VPC network with public and private subnets
module "network" {
  source     = "./modules/network"
  project_id = var.project_id
  region     = var.region
}

# Configure firewall rules for k3s cluster
module "security" {
  source              = "./modules/security"
  project_id          = var.project_id
  network_name        = module.network.network_name
  public_subnet_cidr  = module.network.public_subnet_cidr
  private_subnet_cidr = module.network.private_subnet_cidr
}

# Create k3s cluster: 1 control plane + 3 workers
module "compute" {
  source                 = "./modules/compute"
  project_id             = var.project_id
  zone                   = var.zone
  public_subnet_name     = module.network.public_subnet_name
  private_subnet_name    = module.network.private_subnet_name
  network_name           = module.network.network_name
  vm_image               = var.vm_image
  ssh_user               = var.ssh_user
  ssh_public_key_content = var.ssh_public_key_content
  bucket_name            = var.bucket_name
  service_account_email  = var.service_account_email
}

# Create GCS bucket for database backups
module "storage" {
  source                = "./modules/storage"
  project_id            = var.project_id
  region                = var.region
  service_account_email = var.service_account_email
}

# Output database bucket name
output "database_bucket_name" {
  description = "Name of the database dumps bucket"
  value       = module.storage.database_bucket_name
}

# Configure Cloudflare provider for DNS management
provider "cloudflare" {
  api_token = var.cloudflare_api_token
}

# DNS record for monitoring subdomain
resource "cloudflare_record" "monitoring" {
  zone_id         = var.cloudflare_zone_id
  name            = "monitoring"
  content         = module.compute.k3s_control_plane_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

# DNS record for root domain
resource "cloudflare_record" "root" {
  zone_id         = var.cloudflare_zone_id
  name            = "dock.ink"
  content         = module.compute.k3s_control_plane_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

# DNS record for www subdomain
resource "cloudflare_record" "www" {
  zone_id         = var.cloudflare_zone_id
  name            = "www"
  content         = module.compute.k3s_control_plane_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

# DNS record for api subdomain
resource "cloudflare_record" "api" {
  zone_id         = var.cloudflare_zone_id
  name            = "api"
  content         = module.compute.k3s_control_plane_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

# Optional: Direct access to monitoring tools
# resource "cloudflare_record" "grafana" {
#   zone_id         = var.cloudflare_zone_id
#   name            = "grafana"
#   content         = module.compute.k3s_control_plane_public_ip
#   type            = "A"
#   proxied         = false
#   allow_overwrite = true
# }
