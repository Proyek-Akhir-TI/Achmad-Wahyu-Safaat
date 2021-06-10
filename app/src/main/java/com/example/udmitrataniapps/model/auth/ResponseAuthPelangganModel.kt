package com.example.udmitrataniapps.model.auth

class ResponseAuthPelangganModel {
    var success = 0
    lateinit var message: String
    lateinit var access_token: String
    lateinit var token_id: String
    var pelanggan = PelangganModel()
}