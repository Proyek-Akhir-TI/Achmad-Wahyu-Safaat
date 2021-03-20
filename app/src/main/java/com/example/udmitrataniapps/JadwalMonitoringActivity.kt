package com.example.udmitrataniapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.adapter.JadwalMonitoringAdapter
import com.example.udmitrataniapps.adapter.MonitoringFaseAdapter
import com.example.udmitrataniapps.model.JadwalMonitoring
import kotlinx.android.synthetic.main.activity_jadwal.*

class JadwalMonitoringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var jadwalList = ArrayList<JadwalMonitoring>()
        jadwalList.add(JadwalMonitoring("Edi Hendra", "Kabat, Banyuwangi", "fase awal", "20-01-2021"))
        jadwalList.add(JadwalMonitoring("Edi Santoso", "Glagah, Banyuwangi", "fase vegetatif", "20-01-2021"))
        jadwalList.add(JadwalMonitoring("Selamet Hendra", "Songgon, Banyuwangi", "fase berbung", "20-01-2021"))
        jadwalList.add(JadwalMonitoring("Robi Hendra", "Genteng, Banyuwangi", "fase masak", "20-02-2021"))
        jadwalList.add(JadwalMonitoring("Robi Hendra", "Genteng, Banyuwangi", "fase masak", "20-02-2021"))
        jadwalList.add(JadwalMonitoring("Robi Hendra", "Genteng, Banyuwangi", "fase masak", "20-02-2021"))
        jadwalList.add(JadwalMonitoring("Robi Hendra", "Genteng, Banyuwangi", "fase masak", "20-02-2021"))

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