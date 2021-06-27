package com.example.udmitrataniapps.petugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.SignInActivity
import kotlinx.android.synthetic.main.activity_dashboard_petugas.*

class DashboardPetugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_petugas)
        tv_keluar.setOnClickListener {

            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        menu_notififikasi.setOnClickListener {
            var intent = Intent(this, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        menu_monitoring.setOnClickListener {
            var intent = Intent(this, MonitoringFaseActivity::class.java)
            startActivity(intent)
        }

        menu_jadwal.setOnClickListener {
            var intent = Intent(this, JadwalMonitoringActivity::class.java)
            startActivity(intent)
        }
    }
}