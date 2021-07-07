package com.example.udmitrataniapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.pelanggan.DashboardPelangganActivity
import com.example.udmitrataniapps.petugas.DashboardPetugasActivity
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
            pb_login.visibility = View.VISIBLE
            if (sw_login.isChecked){
                login_pelanggan()
            } else
                login_pegawai()
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getStatusLogin()){
            move_dahsboard_pelanggan()
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

    private fun login_pelanggan() {
        ApiConfig.instancRetrofit.loginPelanggan(
            edt_username.text.toString(),
            edt_password.text.toString()
        ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                pb_login.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    Toast.makeText(this@SignInActivity, "Success, Selamat datang "+ respon.pelanggan.name, Toast.LENGTH_SHORT).show()
                    sharedPref.saveAuthToken(respon.access_token)
                    sharedPref.setStatusLogin(true)
                    sharedPref.setPelanggan(respon.pelanggan)
                    move_dahsboard_pelanggan()
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pb_login.visibility = View.GONE
                Toast.makeText(this@SignInActivity, "Error : " +t.message, Toast.LENGTH_SHORT ).show()
            }

        })
    }


    private fun login_pegawai() {
        ApiConfig.instancRetrofit.loginPegawai(
            edt_username.text.toString(),
            edt_password.text.toString()
        ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                pb_login.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    Toast.makeText(this@SignInActivity, "Success, Selamat datang "+ respon.pegawai.name, Toast.LENGTH_SHORT).show()
                    sharedPref.saveAuthToken(respon.access_token)
                    sharedPref.setStatusLogin(true)
                    sharedPref.setPegawai(respon.pegawai)
                    move_dahsboard_pegawai()
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pb_login.visibility = View.GONE
                Toast.makeText(this@SignInActivity, "Error : " +t.message, Toast.LENGTH_SHORT ).show()
            }

        })
    }

    private fun move_dahsboard_pelanggan() {
        startActivity(Intent(this, DashboardPelangganActivity::class.java))
        finish()
    }

    private fun move_dahsboard_pegawai() {
        Toast.makeText(this, "Selamat datang Sapardi", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DashboardPetugasActivity::class.java))
        finish()
    }
}



