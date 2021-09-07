package com.example.udmitrataniapps.model.pelanggan

class Pesanan {
    var id = 0
    var nomor_induk : String? = null
    lateinit var tgl_sebar : String
    lateinit var tgl_tanam : String
    lateinit var total_benih : String
    lateinit var total_harga_benih : String
    lateinit var total_harga_jasa : String
    var total_biaya = 0
    lateinit var lahan_pelanggan_id : String
    lateinit var stok_padi_id : String
    lateinit var status_pesanan : String
    lateinit var created_at : String
    lateinit var nama_lahan : String
    var stok_padi = StokPadiModel()
}