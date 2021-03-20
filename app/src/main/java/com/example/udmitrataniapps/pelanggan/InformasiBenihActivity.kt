package com.example.udmitrataniapps.pelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.DataBenihAdapter
import com.example.udmitrataniapps.model.DataBenih
import kotlinx.android.synthetic.main.activity_informasi_benih.*

class InformasiBenihActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_benih)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var dataBenihList = ArrayList<DataBenih>()
        dataBenihList.add(DataBenih("Inpari11"))
        dataBenihList.add(DataBenih("ciliwung"))
        dataBenihList.add(DataBenih("citayem"))
        dataBenihList.add(DataBenih("cihampelas"))
        dataBenihList.add(DataBenih("brontoyudo"))


        var dataAdapter = DataBenihAdapter()
        rv_dataBenih.apply {
            var gridLayoutManager = GridLayoutManager(context, 3)
            layoutManager = gridLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setDataBenihRows(dataBenihList)
        dataAdapter.notifyDataSetChanged()

    }
}

