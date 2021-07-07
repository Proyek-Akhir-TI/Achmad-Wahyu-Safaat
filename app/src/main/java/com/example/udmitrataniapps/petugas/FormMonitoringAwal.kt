package com.example.udmitrataniapps.petugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udmitrataniapps.R
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*

class FormMonitoringAwal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_awal)

        btn_submit_awal.setOnClickListener {
            Toast.makeText(this, "Laporan monitoring awal berhasil dikirim", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}