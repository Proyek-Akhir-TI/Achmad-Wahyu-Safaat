package com.example.udmitrataniapps.model.pelanggan

data class PemeriksaanLanjut(
    val barier: String,
    val created_at: String,
    val foto_monitoring: String,
    val id: Int,
    val inbrida_cvl1: Any,
    val inbrida_cvl2: Any,
    val inbrida_cvl3: Any,
    val inbrida_cvl4: Any,
    val isolasi_barat: String,
    val isolasi_selatan: String,
    val isolasi_timur: String,
    val isolasi_utara: String,
    val jenis_pemeriksaan: String,
    val jumlah_contoh_pemeriksaan: String,
    val pemeriksaan_awal_id: Int,
    val perkiraan_produksi: Any,
    val perkiraan_tanggal_panen: Any,
    val serangan_hama: String,
    val sifat_penanaman: String,
    val status_pemeriksaan: Any,
    val updated_at: String,
    val waktu: Any
)