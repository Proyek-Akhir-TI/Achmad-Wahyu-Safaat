package com.example.udmitrataniapps.pelanggan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.SignInActivity
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.pelanggan.lahanpelanggan.LahanPelangganActivity
import com.example.udmitrataniapps.pelanggan.pesananpelanggan.DetailPesananActivity
import com.example.udmitrataniapps.pelanggan.pesananpelanggan.PesanBenihActivity
import com.example.udmitrataniapps.pelanggan.pesananpelanggan.PesananPelangganActivity
import com.example.udmitrataniapps.pelanggan.varietaspadi.InformasiBenihActivity
import kotlinx.android.synthetic.main.activity_dashboard_pelanggan.*

class DashboardPelangganActivity : AppCompatActivity() {

    private lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_pelanggan)

        sharedPref = PreferencesHelper(this)
        val pelanggan = sharedPref.getPelanggan()

        if (pelanggan != null) {
            tv_username.text = pelanggan.nama_lengkap
        }


        tv_keluar.setOnClickListener(){
            sharedPref.setStatusLoginPelanggan(false)
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        menu_informasi_benih.setOnClickListener(){
            val intent = Intent(this, InformasiBenihActivity::class.java)
            startActivity(intent)
        }

        menu_pesan_benih.setOnClickListener(){
            val intent = Intent(this, PesanBenihActivity::class.java)
            startActivity(intent)
        }

        menu_data_lahan.setOnClickListener(){
            val intent = Intent(this, LahanPelangganActivity::class.java)
            startActivity(intent)
        }

        menu_riwayat_pesanan.setOnClickListener {
            startActivity(Intent(this, PesananPelangganActivity::class.java))
        }

        menu_riwayat_monitoring.setOnClickListener {
            startActivity(Intent(this, DetailPesananActivity::class.java))
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }
}



