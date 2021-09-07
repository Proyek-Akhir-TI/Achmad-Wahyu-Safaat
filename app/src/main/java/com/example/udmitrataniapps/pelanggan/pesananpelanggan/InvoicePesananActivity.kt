package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import kotlinx.android.synthetic.main.activity_detail_pesanan.*
import kotlinx.android.synthetic.main.activity_invoice_pesanan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvoicePesananActivity : AppCompatActivity() {
    lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_pesanan)

        sharedPref = PreferencesHelper(this)

        getDetailPesanan()
    }

    private fun getDetailPesanan() {
        ApiConfig.instancRetrofit.getDetailPesanan(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            intent.getIntExtra("id_pesanan", 0)
        ).enqueue(object : Callback<ResponseModel>{
            @SuppressLint("ResourceAsColor")
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful){
                    val respon = response.body()!!.pesanan
                    if (respon.status_pesanan != "Menunggu Pembayaran")
                        tv_status_pembayaran.text = "Lunas" else tv_status_pembayaran.text   = respon.status_pesanan
                    tv_tanggal_pesanan.text     = respon.created_at
                    tv_varietas_padi.text       = respon.stok_padi.varietas_padi.nama_varietas
                    tv_ttl_harga_benih.text     = respon.total_harga_benih
                    tv_ttl_biaya_jasa.text      = respon.total_harga_jasa
                    tv_tgl_sebar.text           = respon.tgl_sebar
                    tv_tgl_tanam.text           = respon.tgl_tanam
                    tv_total_biaya.text         = respon.total_biaya.toString()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@InvoicePesananActivity, "Error : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}