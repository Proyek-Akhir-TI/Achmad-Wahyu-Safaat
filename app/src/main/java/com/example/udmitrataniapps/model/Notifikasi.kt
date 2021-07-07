package com.example.udmitrataniapps.model

class Notifikasi {
    private var title : String = ""
    private var description :String = ""
    private var tglNotifikasi : String = ""

    constructor(title:String, description:String, tglNotifikasi: String) {
        this.title = title
        this.description = description
        this.tglNotifikasi = tglNotifikasi
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String){
        this.title= title
    }

    fun getDesc(): String {
        return description
    }

    fun setDesc(desc: String){
        this.description= desc
    }

    fun getTgl(): String {
        return tglNotifikasi
    }

    fun setTgl(tglNotifikasi: String){
        this.tglNotifikasi= tglNotifikasi
    }
}