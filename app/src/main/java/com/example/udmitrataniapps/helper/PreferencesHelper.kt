package com.example.udmitrataniapps.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.auth.PegawaiModel
import com.example.udmitrataniapps.model.auth.PelangganModel
import com.example.udmitrataniapps.pelanggan.varietaspadi.InformasiBenihActivity
import com.google.gson.Gson

class PreferencesHelper(context: Context) {
    private var sharedPref : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val IS_LOGIN_PEGAWAI = "IS_LOGIN_PEGAWAI"
        const val IS_LOGIN_PELANGGAN = "IS_LOGIN_PELANGGAN"
        const val PELANGGAN = "pelanggan"
        const val PEGAWAI = "pegawai"
    }


    fun saveAuthToken(token: String){
        val editor = sharedPref.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String?{
        return sharedPref.getString(USER_TOKEN, null)
    }

    fun setStatusLoginPelanggan(value: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean(IS_LOGIN_PELANGGAN.toString(), value)
            .apply()
    }

    fun getStatusLoginPelanggan() : Boolean{
        return sharedPref.getBoolean(IS_LOGIN_PELANGGAN, false)
    }

    fun setStatusLoginPegawai(value: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean(IS_LOGIN_PEGAWAI.toString(), value)
            .apply()
    }

    fun getStatusLoginPegawai() : Boolean{
        return sharedPref.getBoolean(IS_LOGIN_PEGAWAI, false)
    }

    fun setPelanggan(value: PelangganModel){
        val data : String = Gson().toJson(value, PelangganModel::class.java)
        sharedPref.edit().putString(PELANGGAN, data).apply()
    }

    fun getPelanggan() : PelangganModel? {
        val data : String = sharedPref.getString(PELANGGAN, null) ?: return null
        return Gson().fromJson<PelangganModel>(data, PelangganModel::class.java)
    }

    fun setPegawai(value: PegawaiModel){
        val data : String = Gson().toJson(value, PegawaiModel::class.java)
        sharedPref.edit().putString(PEGAWAI, data).apply()
    }

    fun getPegawai() : PegawaiModel? {
        val data : String = sharedPref.getString(PEGAWAI, null) ?: return null
        return Gson().fromJson<PegawaiModel>(data, PegawaiModel::class.java)
    }
}