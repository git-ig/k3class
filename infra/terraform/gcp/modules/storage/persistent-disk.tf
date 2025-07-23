# resource "kubernetes_storage_class_v1" "database" {
#   metadata {
#     name = "database-storage"
#   }

#   storage_provisioner    = "pd.csi.storage.gke.io"
#   reclaim_policy        = "Retain"
#   volume_binding_mode   = "WaitForFirstConsumer"
#   allow_volume_expansion = true

#   parameters = {
#     type = "pd-balanced"
#   }
# }

# output "database_storage_class" {
#   description = "Name of the database storage class"
#   value       = kubernetes_storage_class_v1.database.metadata[0].name
# }