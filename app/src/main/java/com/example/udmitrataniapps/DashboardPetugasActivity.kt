package com.example.udmitrataniapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard_petugas.*

class DashboardPetugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_petugas)
        menu_notififikasi.setOnClickListener {
            var intent = Intent(this, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        menu_monitoring.setOnClickListener {
            var intent = Intent(this, MonitoringActivity::class.java)
            startActivity(intent)
        }
    }
}