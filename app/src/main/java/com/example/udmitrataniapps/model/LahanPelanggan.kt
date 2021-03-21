package com.example.udmitrataniapps.model

class LahanPelanggan {
    private var namaLahan : String = ""
    private var luasLahanPelanggan : String = ""
    private var alamatLahanPelanggan : String = ""


    constructor(namaLahan: String, luasLahan: String, alamatLahan: String) {
        this.namaLahan = namaLahan
        this.luasLahanPelanggan = luasLahan
        this.alamatLahanPelanggan = alamatLahan
    }

    fun getNamaLahan() : String{
        return namaLahan
    }

    fun getLuasLahan(): String {
        return luasLahanPelanggan
    }

    fun getAlamatLahan(): String {
        return alamatLahanPelanggan
    }
}