package com.example.udmitrataniapps.model.pegawai

import com.example.udmitrataniapps.model.JadwalMonitoring

class ResponseJadwalMonitoring {
    var success = 0
    lateinit var message : String
    var jadwal_monitoring : ArrayList<com.example.udmitrataniapps.model.pegawai.JadwalMonitoring> = ArrayList()
}