package com.example.udmitrataniapps.model

class MonitoringFase {
    private var namaPemilikLahan : String = ""
    private var lokasiLahan : String = ""
    private var progresFase : String = ""

    constructor(namaPemilikLahan : String, lokasiLahan : String, progesFase : String){
        this.namaPemilikLahan = namaPemilikLahan
        this.lokasiLahan = lokasiLahan
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
}