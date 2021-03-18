package com.example.udmitrataniapps.model

class Notifikasi {
    private var title : String = ""
    private var description :String = ""

    constructor(title:String, description:String) {
        this.title = title
        this.description = description
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
}