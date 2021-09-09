package com.example.udmitrataniapps.model.pegawai

class JadwalMonitoring {
    var id = 0
    lateinit var nomor_induk_pesanan : String
    lateinit var fase_pendahuluan : String
    lateinit var fase_vegetatif : String
    lateinit var fase_berbunga : String
    lateinit var fase_masak : String
    var pesanan : Pesanan = Pesanan()
}