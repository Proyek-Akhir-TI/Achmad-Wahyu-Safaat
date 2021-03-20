package com.example.udmitrataniapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.adapter.MonitoringFaseAdapter
import com.example.udmitrataniapps.model.MonitoringFase
import kotlinx.android.synthetic.main.activity_monitoring.*

class MonitoringFaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var monitoringList = ArrayList<MonitoringFase>()
        monitoringList.add(MonitoringFase("Susanto", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("edi", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Beni", "jawa Timur", "vegetatif"))
        monitoringList.add(MonitoringFase("Anto", "jawa Timur", "vegetatif"))
        monitoringList.add(MonitoringFase("Marina", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Mira", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Santika", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Yuli", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Marimar", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Sabyan", "jawa Timur", "Berbunga"))
        monitoringList.add(MonitoringFase("Andin", "jawa Timur", "Berbunga"))

        var dataAdapter = MonitoringFaseAdapter()
            rv_dataMonitoring.apply {
                var linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager = linearLayoutManager
                adapter = dataAdapter
            }

        dataAdapter.setMonitoringFasePertumbuhan(monitoringList)
        dataAdapter.notifyDataSetChanged()
    }
}