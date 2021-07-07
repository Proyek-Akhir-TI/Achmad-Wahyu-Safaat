package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.MonitoringFase
import kotlinx.android.synthetic.main.item_monitoring.view.*
import java.util.zip.Inflater

class MonitoringFaseAdapter(val callback : Callback): RecyclerView.Adapter<MonitoringFaseAdapter.MyViewHolder>() {

    var monitoring : List<MonitoringFase> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonitoringFaseAdapter.MyViewHolder {
        var viewMonitoring = LayoutInflater.from(parent.context).inflate(R.layout.item_monitoring, parent, false)
        return MyViewHolder(viewMonitoring, callback)
    }

    override fun onBindViewHolder(holder: MonitoringFaseAdapter.MyViewHolder, position: Int) {
        holder.bind(monitoring[position])
    }

    override fun getItemCount(): Int {
        return  monitoring.size
    }

    fun setMonitoringFasePertumbuhan(data: List<MonitoringFase>){
        this.monitoring = data
    }

    inner class MyViewHolder(itemView: View, callback: Callback):RecyclerView.ViewHolder(itemView){
        fun bind(m: MonitoringFase){
            itemView.tv_nama_pemilik.text = m.getNamaPemilikLahan()
            itemView.tv_lokasi.text = m.getLokasiLahan()
            itemView.tv_fase.text = m.getProgresFase()
            itemView.tv_nomor_induk.text = m.getNomorInduk()

            itemView.setOnClickListener{
                callback.onClick(listOf(m))
            }
        }



    }

    interface Callback {
        fun onClick(data: List<MonitoringFase>)
    }



}


