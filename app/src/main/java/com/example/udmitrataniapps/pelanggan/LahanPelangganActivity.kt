package com.example.udmitrataniapps.pelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.LahanPelangganAdapter
import com.example.udmitrataniapps.model.LahanPelanggan
import kotlinx.android.synthetic.main.activity_lahan_pelanggan.*

class LahanPelangganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahan_pelanggan)

        var lahanPelangganList = ArrayList<LahanPelanggan>()
        lahanPelangganList.add(
            LahanPelanggan(
                "Sawah Songgon",
                "1 Hektar",
                "Dusun Sumberarum, Desa Sragi, Kec. Songgon, Kabupaten Banyuwangi, Jawa Timur"
            )
        )
        lahanPelangganList.add(
            LahanPelanggan(
                "Sawah Kemiren",
                "1,5 Hektar",
                "Dusun krajan, Desa Kemiren, Kec. Glagah, Kabupaten Banyuwangi, Jawa Timur"
            )
        )

        var dataAdapter = LahanPelangganAdapter()
        rv_dataLahanPelangan.apply {
            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setDataLahanPelanggan(lahanPelangganList)
        dataAdapter.notifyDataSetChanged()

    }
}