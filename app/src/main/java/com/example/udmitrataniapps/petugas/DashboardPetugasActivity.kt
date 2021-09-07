package com.example.udmitrataniapps.petugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.SignInActivity
import com.example.udmitrataniapps.helper.PreferencesHelper
import kotlinx.android.synthetic.main.activity_dashboard_petugas.*

class DashboardPetugasActivity : AppCompatActivity() {

    lateinit var sharedPref : PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_petugas)

        sharedPref = PreferencesHelper(this)
        val pegawai = sharedPref.getPegawai()
        tv_nama_petugas.text = pegawai?.name

        tv_keluar.setOnClickListener {
            sharedPref.setStatusLoginPegawai(false)
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        menu_notififikasi.setOnClickListener {
            val intent = Intent(this, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        menu_monitoring.setOnClickListener {
            val intent = Intent(this, MonitoringFaseActivity::class.java)
            startActivity(intent)
        }

        menu_jadwal.setOnClickListener {
            val intent = Intent(this, JadwalMonitoringActivity::class.java)
            startActivity(intent)
        }
    }
}