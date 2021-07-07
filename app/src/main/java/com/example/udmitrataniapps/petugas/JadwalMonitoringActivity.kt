package com.example.udmitrataniapps.petugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.JadwalMonitoringAdapter
import com.example.udmitrataniapps.model.JadwalMonitoring
import kotlinx.android.synthetic.main.activity_jadwal.*

class JadwalMonitoringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var jadwalList = ArrayList<JadwalMonitoring>()
        jadwalList.add(JadwalMonitoring("Achmad Wahyu Safaat", "Songgon, Banyuwangi", "fase awal", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Zasiya Fahira", "Glagah, Banyuwangi", "fase vegetatif", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Dika Hermawan", "Giri, Banyuwangi", "fase vegetatif", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Ridwan Ananta", "Tegaldlimo, Banyuwangi", "fase vegetatif", "20-03-2021"))

        var dataAdapter= JadwalMonitoringAdapter()
        rv_jadwal_monitoring.apply {
            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setJadwalMonitoringFase(jadwalList)
        dataAdapter.notifyDataSetChanged()

    }
}