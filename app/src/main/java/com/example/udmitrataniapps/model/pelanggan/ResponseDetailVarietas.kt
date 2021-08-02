package com.example.udmitrataniapps.model.pelanggan

class ResponseDetailVarietas {
    var success = 0
    lateinit var message: String
    lateinit var foto_varietas: String
    lateinit var harga: String
    lateinit var total_stok: String
    var stok_padi : ArrayList<StokPadiModel> = ArrayList()
}