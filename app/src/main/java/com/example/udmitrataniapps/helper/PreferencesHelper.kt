package com.example.udmitrataniapps.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.udmitrataniapps.R
import java.nio.channels.spi.AbstractSelectionKey

class PreferencesHelper (context: Context) {
    private var sharedPref : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val IS_LOGIN = "IS_LOGIN"
    }

    fun saveAuthToken(token: String){
        val editor = sharedPref.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String?{
        return sharedPref.getString(USER_TOKEN, null)
    }

    fun setStatusLogin(key: String, value: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean(IS_LOGIN.toString(), value)
            .apply()
    }

    fun getStatusLogin(key: String) : Boolean{
        return sharedPref.getBoolean(IS_LOGIN, false)
    }
}