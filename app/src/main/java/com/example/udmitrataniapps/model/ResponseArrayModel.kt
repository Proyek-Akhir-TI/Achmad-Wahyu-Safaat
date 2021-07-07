package com.example.udmitrataniapps.model

import com.example.udmitrataniapps.model.pelanggan.DataLahan
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi

class ResponseArrayModel {
    var success = 0
    lateinit var message: String
    var data_lahan: ArrayList<DataLahan> = ArrayList()
    var varietas_padi : ArrayList<VarietasPadi> = ArrayList()
}
