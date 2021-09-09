package com.example.udmitrataniapps.petugas

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
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
import java.util.*

class FormMonitoringAwal : AppCompatActivity() {
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var address: Address
    private val locationPermissionCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_awal)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        btn_lokasi.setOnClickListener {
            getLocation()
        }



    }



    override fun onResume() {
        super.onResume()

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
                var geocoder = Geocoder(this)
                val addresses : List<Address>
                geocoder = Geocoder(applicationContext, Locale.getDefault())
                addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                val address : String = addresses[0].getAddressLine(0)
                tv_lokasi_sekarang.text = address
                Toast.makeText(applicationContext, "Lokasi berhasil diperbarui ", Toast.LENGTH_SHORT).show()
            }
        }
    }



}


