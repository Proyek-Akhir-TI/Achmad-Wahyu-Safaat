package com.example.udmitrataniapps.model

import com.example.udmitrataniapps.model.pelanggan.DataLahan
import com.example.udmitrataniapps.model.pelanggan.Pesanan
import com.example.udmitrataniapps.model.pelanggan.StokPadiModel
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi

class ResponseArrayModel {
    var success = 0
    lateinit var message: String
    var data_lahan: ArrayList<DataLahan> = ArrayList()
    var varietas_padi : ArrayList<VarietasPadi> = ArrayList()
    var stok_padi : ArrayList<StokPadiModel> = ArrayList()
    var pesanans : ArrayList<Pesanan> = ArrayList()
}
