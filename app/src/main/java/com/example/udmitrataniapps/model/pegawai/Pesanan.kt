package com.example.udmitrataniapps.model.pegawai

import androidx.annotation.Nullable

class Pesanan {
    var id = 0
    lateinit var status_pesanan : String
    var nomor_induk : String? = null
    var lahan_pelanggan : LahanPelanggan = null ?: LahanPelanggan()
    var pemeriksaan_awal : PemeriksaanAwal = null ?: PemeriksaanAwal()
    class LahanPelanggan {
        lateinit var alamat : String
        var pelanggan : Pelanggan = Pelanggan()

        class Pelanggan {
            var nama_lengkap : String? = null
            var telepon : String? = null
        }
    }
    class PemeriksaanAwal{
        var id = 0
    }
}