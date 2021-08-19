package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.PesanansPelangganAdapter
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseArrayModel
import com.example.udmitrataniapps.model.pelanggan.Pesanan
import kotlinx.android.synthetic.main.activity_pesanan_pelanggan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PesananPelangganActivity : AppCompatActivity(), PesanansPelangganAdapter.Callback {
    lateinit var sharedPref : PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan_pelanggan)
        sharedPref = PreferencesHelper(this)

        val dataAdapter = PesanansPelangganAdapter(this)
        getPesanans(dataAdapter)
        dataAdapter.notifyDataSetChanged()
    }

    private fun getPesanans(dataAdapter: PesanansPelangganAdapter) {
        ApiConfig.instancRetrofit.getPesanansPelanggan("Bearer ${sharedPref.fetchAuthToken()}")
            .enqueue(object : Callback<ResponseArrayModel>{
                override fun onResponse(
                    call: Call<ResponseArrayModel>,
                    response: Response<ResponseArrayModel>
                ) {
                    if (response.isSuccessful){
                        rv_dataPesanans.apply {
                            val linearLayoutManager = LinearLayoutManager(this@PesananPelangganActivity)
                            layoutManager = linearLayoutManager
                            adapter = dataAdapter
                        }
                        dataAdapter.setDataPesanans(response.body()!!.pesanans)
                    }
                }

                override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {

                }

            })
    }

    override fun onClick(pesanan: Pesanan) {
        when (pesanan.status_pesanan){
            "Belum Bayar" -> startActivity(Intent(this, InvoicePesananActivity::class.java)
                .putExtra("id_pesanan", pesanan.id))
            else -> startActivity(Intent(this, DetailPesananActivity::class.java)
                .putExtra("id_pesanan", pesanan.id))
        }
    }
}