package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.pelanggan.PemeriksaanAwal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPesananActivity : AppCompatActivity() {

    lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pesanan)
        sharedPref = PreferencesHelper(this)

        getRiwayatMonitoring()
    }

    private fun getRiwayatMonitoring() {
        val pesanan_id = intent.getIntExtra("pesanan_id", 0)
        ApiConfig.instancRetrofit.getRiwayatMonitoring(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            pesanan_id
        ).enqueue(object : Callback<PemeriksaanAwal>{
            override fun onResponse(
                call: Call<PemeriksaanAwal>,
                response: Response<PemeriksaanAwal>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@DetailPesananActivity, "Berhasi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PemeriksaanAwal>, t: Throwable) {
                Toast.makeText(this@DetailPesananActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}