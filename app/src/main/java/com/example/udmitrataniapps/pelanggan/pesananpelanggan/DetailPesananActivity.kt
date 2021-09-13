package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.MonitoringLanjutAdapter
import com.example.udmitrataniapps.adapter.PesanansPelangganAdapter
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.pelanggan.PemeriksaanAwal
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.toGone
import kotlinx.android.synthetic.main.activity_detail_pesanan.*
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*
import kotlinx.android.synthetic.main.activity_pesanan_pelanggan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPesananActivity : BaseActivity() {

    lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pesanan)
        sharedPref = PreferencesHelper(this)

        val dataAdapter = MonitoringLanjutAdapter()
        getRiwayatMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun getRiwayatMonitoring(dataAdapter : MonitoringLanjutAdapter) {
        progress.show()
        val pesanan_id = intent.getIntExtra("pesanan_id", 0)
        ApiConfig.instancRetrofit.getRiwayatMonitoring(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            pesanan_id
        ).enqueue(object : Callback<PemeriksaanAwal>{
            override fun onResponse(
                call: Call<PemeriksaanAwal>,
                response: Response<PemeriksaanAwal>
            ) {
                progress.dismiss()
                val resp = response.body()
//                if (resp != null){
//                    dialog_empty_detail.toGone()
//                }
                if (response.isSuccessful){
                    tv_letak_areal.text = "Kebenaran Letak Areal : "+resp?.letak_areal
                    tv_luas_areal.text = "Kebenaran Luas Areal : "+resp?.luas_areal
                    tv_isolasi.text = "Kebenaran Isolasi : "+resp?.isolasi
                    tv_sejarah_lapang.text = "Kebenaran Sejarah Lapang : "+resp?.sejarah_lapang
                    tv_asal_jumlah.text = "Kebenaran Asal Jumlah Benih : "+resp?.asal_jumlah_benih
                    tv_catatan.text = "Catatan Pemeriksaan : " +resp?.catatan
                    tv_kesimpulan.text = "Kesimpulan Akhir : "+resp?.kesimpulan

                    rv_pemeriksaan_lanju.apply {
                        val linearLayoutManager = LinearLayoutManager(this@DetailPesananActivity)
                        layoutManager = linearLayoutManager
                        adapter = dataAdapter
                    }
                    dataAdapter.setMonitoringLanjutData(response.body()!!.pemeriksaan_lanjut)
                }
            }

            override fun onFailure(call: Call<PemeriksaanAwal>, t: Throwable) {
                progress.dismiss()
                Toast.makeText(this@DetailPesananActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}