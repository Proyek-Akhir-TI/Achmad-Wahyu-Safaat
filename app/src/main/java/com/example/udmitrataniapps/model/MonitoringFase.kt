package com.example.udmitrataniapps.model

class MonitoringFase {
    private var namaPemilikLahan : String = ""
    private var lokasiLahan : String = ""
    private var nomorInduk : String = ""
    private var progresFase : String = ""

    constructor(namaPemilikLahan : String, lokasiLahan : String, nomorInduk : String, progesFase : String){
        this.namaPemilikLahan = namaPemilikLahan
        this.lokasiLahan = lokasiLahan
        this.nomorInduk = nomorInduk
        this.progresFase = progesFase
    }

    fun getNamaPemilikLahan(): String {
        return namaPemilikLahan
    }

    fun setNamaPemilikLahan(namaPemilik: String) {
        this.namaPemilikLahan = namaPemilik
    }

    fun getLokasiLahan(): String {
        return lokasiLahan
    }

    fun setLokasiLahan(lokasi: String) {
        this.lokasiLahan = lokasi
    }

    fun getProgresFase(): String {
        return progresFase
    }

    fun setProgresFase(fase: String) {
        this.progresFase = fase
    }

    fun getNomorInduk(): String {
        return nomorInduk
    }
}