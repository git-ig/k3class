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
  }
  backend "gcs" {
    bucket = var.bucket_name
    prefix = "terraform/state"
  }
}

provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

module "network" {
  source     = "./modules/network"
  project_id = var.project_id
  region     = var.region
}

module "security" {
  source              = "./modules/security"
  project_id          = var.project_id
  network_name        = module.network.network_name
  public_subnet_cidr  = module.network.public_subnet_cidr
  private_subnet_cidr = module.network.private_subnet_cidr
}

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
}

module "storage" {
  source                = "./modules/storage"
  project_id            = var.project_id
  region                = var.region
  service_account_email = var.service_account_email
}

output "database_bucket_name" {
  description = "Name of the database dumps bucket"
  value       = module.storage.database_bucket_name
}

provider "cloudflare" {
  api_token = var.cloudflare_api_token
}

resource "cloudflare_record" "monitoring" {
  zone_id         = var.cloudflare_zone_id
  name            = "monitoring"
  content         = module.compute.bastion_public_ip # Заменить value на content
  type            = "A"
  proxied         = true
  allow_overwrite = true # Добавить эту строку
}

resource "cloudflare_record" "root" {
  zone_id         = var.cloudflare_zone_id
  name            = "dock.ink"
  content         = module.compute.bastion_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

resource "cloudflare_record" "www" {
  zone_id         = var.cloudflare_zone_id
  name            = "www"
  content         = module.compute.bastion_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

resource "cloudflare_record" "api" {
  zone_id         = var.cloudflare_zone_id
  name            = "api"
  content         = module.compute.bastion_public_ip
  type            = "A"
  proxied         = true
  allow_overwrite = true
}

# resource "cloudflare_record" "grafana" {
#   zone_id         = var.cloudflare_zone_id
#   name            = "grafana"
#   content         = module.compute.bastion_public_ip
#   type            = "A"
#   proxied         = false
#   allow_overwrite = false
# }

# resource "cloudflare_record" "prometheus" {
#   zone_id         = var.cloudflare_zone_id
#   name            = "prometheus"
#   content         = module.compute.bastion_public_ip
#   type            = "A"
#   proxied         = false
#   allow_overwrite = false
# }
