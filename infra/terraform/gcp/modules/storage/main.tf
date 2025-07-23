# get project number for GCS service account
data "google_project" "project" {
  project_id = var.project_id
}

# KMS key ring
resource "google_kms_key_ring" "database" {
  name     = "database-keyring-${random_id.suffix.hex}"
  location = var.region
  project  = var.project_id
}

# KMS crypto key
resource "google_kms_crypto_key" "database_key" {
  name     = "database-encryption-key"
  key_ring = google_kms_key_ring.database.id
}

# IAM for GCS service account on KMS key
resource "google_kms_crypto_key_iam_member" "storage_service_account" {
  crypto_key_id = google_kms_crypto_key.database_key.id
  role          = "roles/cloudkms.cryptoKeyEncrypterDecrypter"
  member        = "serviceAccount:service-${data.google_project.project.number}@gs-project-accounts.iam.gserviceaccount.com"
}

# Storage bucket with KMS encryption
resource "google_storage_bucket" "database_dumps" {
  name     = "${var.project_id}-database-dumps"
  location = var.region

  uniform_bucket_level_access = true

  versioning {
    enabled = true
  }

  lifecycle_rule {
    condition {
      age = 30
    }
    action {
      type = "Delete"
    }
  }

  encryption {
    default_kms_key_name = google_kms_crypto_key.database_key.id
  }

  depends_on = [google_kms_crypto_key_iam_member.storage_service_account]
}

# IAM for service account
resource "google_storage_bucket_iam_member" "database_access" {
  bucket = google_storage_bucket.database_dumps.name
  role   = "roles/storage.objectViewer"
  member = "serviceAccount:${var.service_account_email}"
}

# Output
output "database_bucket_name" {
  description = "Name of the database dumps bucket"
  value       = google_storage_bucket.database_dumps.name
}
