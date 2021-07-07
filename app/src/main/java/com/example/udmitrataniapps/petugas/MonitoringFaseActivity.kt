package com.example.udmitrataniapps.petugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.MonitoringFaseAdapter
import com.example.udmitrataniapps.model.MonitoringFase
import kotlinx.android.synthetic.main.activity_monitoring.*

class MonitoringFaseActivity : AppCompatActivity(), MonitoringFaseAdapter.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val monitoringList = ArrayList<MonitoringFase>()
        monitoringList.add(MonitoringFase("Dika Hermawan",
            "Sawah Grogol, Dusun pelinggihan, Desa Grogol, Kec. Giri, Kabupaten Banyuwangi",
            "psn/001/93", "Fase Masak" ))
        monitoringList.add(MonitoringFase("Ridwan Ananta",
            "Sawah Tegaldlimo, Desa Kalipait, kec. Tegaldlimo, Kabupaten Banyuwangi",
            "psn/001/123", "Fase Generatif" ))
        monitoringList.add(MonitoringFase("Achmad Wahyu Safaat",
            "Sawah Songgon, Dusun Krajan RT.01/RW.01 Desa Songgon, kec. Songgon, Kabupaten Banyuwangi",
            "psn/001/153", "Fase Vegetatif" ))
        monitoringList.add(MonitoringFase("Zasiya Fahira",
            "Sawah Kalipuro, Dusun klatak RT.01/RW.01 Desa Klatak, kec. Kalipuro, Kabupaten Banyuwangi",
            "psn/001/170", "Fase Vegetatif" ))
        monitoringList.add(MonitoringFase("Achmad Wahyu Safaat",
            "Sawah Tamansuruh, Dusun Krajan RT.01/RW.01 Desa Tamansuruh, kec. Glagah, Kabupaten Banyuwangi",
            "psn/001/211", "Fase Awal" ))



        val dataAdapter = MonitoringFaseAdapter(this)
            rv_dataMonitoring.apply {
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager = linearLayoutManager
                adapter = dataAdapter
            }

        dataAdapter.setMonitoringFasePertumbuhan(monitoringList)
        dataAdapter.notifyDataSetChanged()
    }

    override fun onClick(data: List<MonitoringFase>) {
        startActivity(Intent(this, FormMonitoringAwal::class.java))
    }
}