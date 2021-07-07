package com.example.udmitrataniapps.petugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.NotifikasiPetugasAdapter
import com.example.udmitrataniapps.model.Notifikasi
import kotlinx.android.synthetic.main.activity_notifikasi.*

class NotifikasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val notifikasiList = ArrayList<Notifikasi>()
        notifikasiList.add(Notifikasi("Jadwal baru Monitoring", "Ada 5 lahan Baru yang harus kamu monitoring", "25 Juni 2021"))
        notifikasiList.add(Notifikasi("Laporan Monitoring", "Kamu Telah melakukan Monitoring di 2 lahan", "15 Juni 2021"))
        notifikasiList.add(Notifikasi("Jadwal baru Monitoring", "Ada 2 lahan Baru yang harus kamu monitoring", "07 Juni 2021"))


        val dataAdapter = NotifikasiPetugasAdapter()
        rv_dataNotifikasi.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setNotifikasiPetugas(notifikasiList)
        dataAdapter.notifyDataSetChanged()

    }
}