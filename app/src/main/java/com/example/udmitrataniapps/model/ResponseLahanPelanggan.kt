package com.example.udmitrataniapps.model

import com.example.udmitrataniapps.model.pelanggan.DataLahan

class ResponseLahanPelanggan {
    var success = 0
    lateinit var message: String
    var data_lahan: ArrayList<DataLahan> = ArrayList()
}
