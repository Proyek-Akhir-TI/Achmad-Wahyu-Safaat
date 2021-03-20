package com.example.udmitrataniapps.model

class DataBenih {
    private var namaBenih : String? = ""

    constructor(namaBenih: String){
        this.namaBenih = namaBenih
    }

    fun getBenih(): String? {
        return namaBenih
    }
}