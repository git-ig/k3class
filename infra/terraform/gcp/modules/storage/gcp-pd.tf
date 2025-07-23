###############################################################################
# GCP Persistent-Disk CSI-driver 
###############################################################################
resource "helm_release" "gcp_pd_csi" {
  name       = "gcp-pd-csi-driver"
  repository = "https://kubernetes-sigs.github.io/gcp-compute-persistent-disk-csi-driver"
  chart      = "gcp-pd-csi-driver"
  version    = "1.10.0"
  namespace  = "kube-system"
  create_namespace = false
}

###############################################################################
# StorageClass dynamic creating of PD-volumes up to 1 GiB
###############################################################################
resource "kubernetes_storage_class_v1" "pd_1g" {
  metadata {
    name = "pd-1g"
  }

  provisioner          = "pd.csi.storage.gke.io"
  reclaim_policy       = "Retain"
  volume_binding_mode  = "WaitForFirstConsumer"
  allow_volume_expansion = true

  parameters = {
    type = "pd-balanced"
  }

  depends_on = [helm_release.gcp_pd_csi]
}

output "database_storage_class" {
  value = kubernetes_storage_class_v1.pd_1g.metadata[0].name
}