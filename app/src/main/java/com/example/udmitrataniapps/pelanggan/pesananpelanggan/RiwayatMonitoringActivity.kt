package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.PesanansPelangganAdapter
import com.example.udmitrataniapps.adapter.RiwayatMonitoringAdapter
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseArrayModel
import com.example.udmitrataniapps.model.pelanggan.Pesanan
import com.inyongtisto.myhelper.extension.showErrorDialog
import kotlinx.android.synthetic.main.activity_pesanan_pelanggan.*
import kotlinx.android.synthetic.main.activity_pesanan_pelanggan.rv_dataPesanans
import kotlinx.android.synthetic.main.activity_riwayat_monitoring.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatMonitoringActivity : AppCompatActivity(), RiwayatMonitoringAdapter.Callback {
    lateinit var sharedPref : PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_monitoring)
        sharedPref = PreferencesHelper(this)

        val dataAdapter = RiwayatMonitoringAdapter(this)
        getPesanans(dataAdapter)
        dataAdapter.notifyDataSetChanged()
    }


    private fun getPesanans(dataAdapter: RiwayatMonitoringAdapter) {
        ApiConfig.instancRetrofit.getMonitoringPelanggan("Bearer ${sharedPref.fetchAuthToken()}")
            .enqueue(object : Callback<ResponseArrayModel> {
                override fun onResponse(
                    call: Call<ResponseArrayModel>,
                    response: Response<ResponseArrayModel>
                ) {
                    if (response.isSuccessful){
                        rv_dataPesanans.apply {
                            val linearLayoutManager = LinearLayoutManager(this@RiwayatMonitoringActivity)
                            layoutManager = linearLayoutManager
                            adapter = dataAdapter
                        }
                        dataAdapter.setDataPesanans(response.body()!!.pesanans)
                    }
                }

                override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {
                    showErrorDialog(t.message!!)
                }

            })
    }

    override fun onClick(data: Pesanan) {
        startActivity(
            Intent(this, DetailPesananActivity  ::class.java)
            .putExtra("pesanan_id", data.id))
    }
}