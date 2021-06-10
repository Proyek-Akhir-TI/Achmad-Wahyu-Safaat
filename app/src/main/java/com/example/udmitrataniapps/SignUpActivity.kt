package com.example.udmitrataniapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.model.auth.ResponseAuthPelangganModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_daftar.setOnClickListener {
            register_pelanggan()
        }
    }


    private fun register_pelanggan() {
        cek_input_field()

        ApiConfig.instancRetrofit.register_pelanggan(
            edt_nama_lengkap.text.toString(),
            edt_username.text.toString(),
            edt_password.text.toString(),
            edt_nik.text.toString(),
            edt_alamat.text.toString(),
            edt_phone.text.toString()
        ).enqueue(object : Callback<ResponseAuthPelangganModel>{
            override fun onResponse(
                call: Call<ResponseAuthPelangganModel>,
                response: Response<ResponseAuthPelangganModel>
            ) {
                val respon = response.body()!!
                if (respon.success==1){
                    Toast.makeText(this@SignUpActivity, "Selamat datang : "+respon.pelanggan.name, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "Error : "+respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponseAuthPelangganModel>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Error : "+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cek_input_field() {
        if (edt_nama_lengkap.text?.isEmpty()!!){
            edt_nama_lengkap.error = "Kolom Nama Lengkap tidak boleh kosong"
            edt_nama_lengkap.requestFocus()
            return
        }else if (edt_username.text?.isEmpty()!!){
            edt_username.error = "Kolom NIK harus diisi"
            edt_username.requestFocus()
            return
        }else if (edt_password.text?.isEmpty()!!){
            edt_password.error = "Kata Sandi harus diisi"
            edt_password.requestFocus()
            return
        }else if (edt_nik.text?.isEmpty()!!){
            edt_nik.error = "Kolom NIK harus diisi"
            edt_nik.requestFocus()
            return
        }else if (edt_phone.text?.isEmpty()!!){
            edt_phone.error = "Nomor Telepon harus diisi"
            edt_phone.requestFocus()
            return
        }
    }


}

