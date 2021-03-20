package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.JadwalMonitoring
import kotlinx.android.synthetic.main.item_jadwal.view.*

class JadwalMonitoringAdapter: RecyclerView.Adapter<JadwalMonitoringAdapter.MyViewHolder>() {

    var jadwalMonitoring : List<JadwalMonitoring> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var viewJadwalMonitoring = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return MyViewHolder(viewJadwalMonitoring)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(jadwalMonitoring[position])
    }

    override fun getItemCount(): Int {
        return jadwalMonitoring.size
    }

    fun setJadwalMonitoringFase(data: List<JadwalMonitoring>){
        this.jadwalMonitoring = data
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(j: JadwalMonitoring){
            itemView.tv_nama_pemilik.text = j.getNamaPemilik()
            itemView.tv_fase.text = j.getFase()
            itemView.tv_tanggal.text = j.getTanggal()
            itemView.tv_lokasi.text = j.getLokasi()
        }
    }
}

