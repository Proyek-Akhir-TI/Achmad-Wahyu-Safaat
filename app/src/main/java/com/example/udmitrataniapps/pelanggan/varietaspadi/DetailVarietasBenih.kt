package com.example.udmitrataniapps.pelanggan.varietaspadi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.pelanggan.ResponseDetailVarietas
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_varietas_benih.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DetailVarietasBenih : AppCompatActivity() {
    lateinit var sharedPref : PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_varietas_benih)
        sharedPref = PreferencesHelper(this)

        initApp()
        fetchDetailByIdVarietas()
    }

    private fun initApp() {
        tv_desc_varietas.text = intent.getStringExtra("deskripsi")
        tv_nama_varietas.text = intent.getStringExtra("nama_varietas")
        val image = "http://192.168.1.107/proyek_akhir/public/images/${intent.getStringExtra("foto")}"
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.benih)
            .error(R.drawable.benih)
            .into(iv_foto_detail)
    }

    private fun fetchDetailByIdVarietas() {
        val idVarietas = intent.getStringExtra("id")
        ApiConfig.instancRetrofit.fetchVarietasById(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            idVarietas = idVarietas
        ).enqueue(object : Callback<ResponseDetailVarietas>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ResponseDetailVarietas>,
                response: Response<ResponseDetailVarietas>
            ) {
                val respon = response.body()!!
                if (response.isSuccessful){
                    Toast.makeText(this@DetailVarietasBenih, "berhasil", Toast.LENGTH_SHORT).show()
                    tv_harga_benih.text = "Harga : "+respon.harga
                    tv_stok_benih.text = "Total Stok : "+respon.total_stok
                }
            }

            override fun onFailure(call: Call<ResponseDetailVarietas>, t: Throwable) {

            }

        })

    }
}