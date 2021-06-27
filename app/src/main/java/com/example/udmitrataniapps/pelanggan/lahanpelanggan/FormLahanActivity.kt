package com.example.udmitrataniapps.pelanggan.lahanpelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.model.pelanggan.DataLahan
import kotlinx.android.synthetic.main.activity_form_lahan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormLahanActivity : AppCompatActivity() {
    private lateinit var sharedPref : PreferencesHelper
    private lateinit var dataLahan : DataLahan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_lahan)

        sharedPref = PreferencesHelper(this)
        cekStatusButton()

    }



    private fun cekStatusButton() {
        val status = intent.getBooleanExtra("statusButton", false)
        if (status){
            btn_simpan_lahan.setOnClickListener {
                cekInput()
                sendDataLahan()
            }
        }else {
            btn_simpan_lahan.text = "Update Lahan"
            dataLahan  = intent.getParcelableExtra("dataLahan")!!
            var idLahan = dataLahan.id
            edt_nama_lahan.setText(dataLahan.nama_lahan)
            edt_alamat_lahan.setText(dataLahan.alamat)
            edt_luas_lahan.setText(dataLahan.luas_lahan.toString())
            edt_sejarah_lahan.setText(dataLahan.sejarah_lahan)
            btn_simpan_lahan.setOnClickListener {
                updateDataLahan(idLahan)
            }

        }
    }


    private fun cekInput(){
        if (edt_nama_lahan.text?.isEmpty()!!){
            edt_nama_lahan.error = "Nama lahan wajib diisi"
            edt_nama_lahan.requestFocus()
            return
        }else if (edt_alamat_lahan.text?.isEmpty()!!){
            edt_alamat_lahan.error = "alamat lahan wajib diisi"
            edt_alamat_lahan.requestFocus()
            return
        }else if (edt_luas_lahan.text?.isEmpty()!!){
            edt_luas_lahan.error = "luas lahan wajib diisi"
            edt_luas_lahan.requestFocus()
            return
        } else if (edt_sejarah_lahan.text?.isEmpty()!!) {
            edt_sejarah_lahan.error = "sejarah lahan wajib diisi"
            edt_sejarah_lahan.requestFocus()
            return
        }
    }

    private fun updateDataLahan(idLahan: Int) {
        ApiConfig.instancRetrofit.updateLahan(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            idLahan,
            edt_nama_lahan.text.toString(),
            edt_alamat_lahan.text.toString(),
            edt_luas_lahan.text.toString(),
            edt_sejarah_lahan.text.toString()
        ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful){
                    Toast.makeText(this@FormLahanActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@FormLahanActivity, "Error : "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun sendDataLahan(){
        ApiConfig.instancRetrofit.tambahLahan(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            edt_nama_lahan.text.toString(),
            edt_alamat_lahan.text.toString(),
            edt_luas_lahan.text.toString(),
            edt_sejarah_lahan.text.toString()
        ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@FormLahanActivity, response.body()!!.data_lahan.nama_lahan+" berhasil disimpan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@FormLahanActivity, LahanPelangganActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@FormLahanActivity, "Error : "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}

