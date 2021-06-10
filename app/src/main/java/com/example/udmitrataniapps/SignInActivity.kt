package com.example.udmitrataniapps

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.auth.ResponseAuthPelangganModel
import com.example.udmitrataniapps.pelanggan.DashboardPelangganActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var sharedPref: PreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sharedPref = PreferencesHelper(this)

        tv_to_register.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener{
            form_validation()
            if (sw_login.isChecked){
                login_pegawai()
            } else
                login_pelanggan()
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getStatusLogin("IS_LOGIN")){
            move_intent()
        }
    }

    private fun form_validation() {
        if (edt_username.text?.isEmpty()!!){
            edt_username.error = "Nama Pengguna wajib diisi"
            edt_username.requestFocus()
            return
        } else if (edt_password.text?.isEmpty()!!){
            edt_password.error = "Password wajib diisi"
            edt_password.requestFocus()
            return
        }
    }

    private fun login_pegawai() {

        ApiConfig.instancRetrofit.login_pelanggan(
            edt_username.text.toString(),
            edt_password.text.toString()
        ).enqueue(object : Callback<ResponseAuthPelangganModel>{
            override fun onResponse(
                call: Call<ResponseAuthPelangganModel>,
                response: Response<ResponseAuthPelangganModel>
            ) {
                val respon = response.body()!!
                if (respon.success == 1){
                    Toast.makeText(this@SignInActivity, "Success, Selamat datang "+ respon.token_id, Toast.LENGTH_SHORT).show()
                    sharedPref.saveAuthToken(respon.access_token)
                    sharedPref.setStatusLogin("IS_LOGIN", true)
                    move_intent()
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseAuthPelangganModel>, t: Throwable) {
                Toast.makeText(this@SignInActivity, "Error : " +t.message, Toast.LENGTH_SHORT ).show()
            }

        })
    }


    private fun login_pelanggan() {
        Log.d(String.toString(), "Login pegawai berhasil")
    }

    private fun move_intent() {
        startActivity(Intent(this, DashboardPelangganActivity::class.java))
    }
}



