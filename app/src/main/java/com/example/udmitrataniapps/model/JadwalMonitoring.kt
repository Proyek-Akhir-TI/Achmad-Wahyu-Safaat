package com.example.udmitrataniapps.model

class JadwalMonitoring {
    private var namaPemilikLahan : String = ""
    private var lokasiLahan : String = ""
    private var progresFase : String = ""
    private var tanggalMonitoring : String = ""

    constructor(namaPemilik : String, lokasi : String, fase : String, tanggal : String){
        this.namaPemilikLahan = namaPemilik
        this.lokasiLahan = lokasi
        this.progresFase = fase
        this.tanggalMonitoring = tanggal
    }

    fun getNamaPemilik(): String{
        return namaPemilikLahan
    }

    fun getLokasi(): String {
        return lokasiLahan
    }

    fun getFase(): String {
        return progresFase
    }

    fun getTanggal(): String {
        return tanggalMonitoring
    }
}