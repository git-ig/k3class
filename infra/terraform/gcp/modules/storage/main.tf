resource "google_storage_bucket" "database_dumps" {
  name     = "${var.project_id}-database-dumps"
  location = var.region

  # Security config
  uniform_bucket_level_access = true

  # Versioning of backups
  versioning {
    enabled = true
  }

  # objects lifecycle
  lifecycle_rule {
    condition {
      age = 30
    }
    action {
      type = "Delete"
    }
  }

  # encryption
  encryption {
    default_kms_key_name = google_kms_crypto_key.database_key.id
  }
}

# KMS key for encryption
resource "google_kms_key_ring" "database" {
  name     = "database-keyring-2"
  location = "us-central1"
  project  = var.project_id
}

resource "google_kms_crypto_key" "database_key" {
  name     = "database-encryption-key"
  key_ring = google_kms_key_ring.database.id
}

# IAM rights for bucket access
resource "google_storage_bucket_iam_member" "database_access" {
  bucket = google_storage_bucket.database_dumps.name
  role   = "roles/storage.objectViewer"
  member = "serviceAccount:${var.service_account_email}"
}

# Output for using in other modules
output "database_bucket_name" {
  description = "Name of the database dumps bucket"
  value       = google_storage_bucket.database_dumps.name
}
