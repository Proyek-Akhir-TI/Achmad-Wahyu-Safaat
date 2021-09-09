package com.example.udmitrataniapps.petugas

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.example.udmitrataniapps.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*

class FormMonitoringAwal : AppCompatActivity() {
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private val locationPermissionCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_awal)

        btn_submit_awal.setOnClickListener {
            Toast.makeText(this, "Laporan monitoring awal berhasil dikirim", Toast.LENGTH_SHORT).show()
            finish()
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation()
    }

    private fun getLocation() {
        val task:Task<Location> = fusedLocationProviderClient.lastLocation
        if (
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                Toast.makeText(applicationContext, "${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


