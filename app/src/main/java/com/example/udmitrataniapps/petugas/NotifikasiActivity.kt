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

        var notifikasiList = ArrayList<Notifikasi>()
        notifikasiList.add(Notifikasi("Notifikasi 1", "detail Notifikasi 1"))
        notifikasiList.add(Notifikasi("Notifikasi 2", "detail Notifikasi 2"))
        notifikasiList.add(Notifikasi("Notifikasi 3", "detail Notifikasi 3"))

        var dataAdapter = NotifikasiPetugasAdapter()
        rv_dataNotifikasi.apply {
            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setNotifikasiPetugas(notifikasiList)
        dataAdapter.notifyDataSetChanged()

    }
}