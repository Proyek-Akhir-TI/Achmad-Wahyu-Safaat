package com.example.udmitrataniapps.model.pelanggan

data class PemeriksaanAwal(
    val asal_jumlah_benih: String,
    val catatan: String,
    val created_at: String,
    val id: Int,
    val isolasi: String,
    val kesimpulan: String,
    val letak_areal: String,
    val luas_areal: String,
    val pemeriksaan_lanjut: List<PemeriksaanLanjut>,
    val pesanan_id: Int,
    val sejarah_lapang: String,
    val updated_at: String,
    val users_id: Int
)