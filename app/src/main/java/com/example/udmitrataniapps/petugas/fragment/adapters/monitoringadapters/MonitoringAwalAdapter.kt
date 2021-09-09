package com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.VarietasBenihAdapter
import com.example.udmitrataniapps.model.pegawai.JadwalMonitoring
import com.example.udmitrataniapps.model.pegawai.Pesanan
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi
import kotlinx.android.synthetic.main.item_monitoring.view.*
import kotlinx.coroutines.flow.callbackFlow

class MonitoringAwalAdapter(val callback : Callback) : RecyclerView.Adapter<MonitoringAwalAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View, callback: Callback) : RecyclerView.ViewHolder(itemView) {
        fun bind(monitoring: Pesanan) {
            itemView.tv_nama_pemilik.text = monitoring.lahan_pelanggan.pelanggan.nama_lengkap
            itemView.tv_nomor_induk.text = monitoring.nomor_induk ?: "Nomor Induk Belum Tesedia"
            itemView.tv_lokasi.text = monitoring.lahan_pelanggan.alamat
            if (monitoring.status_pesanan == "Lunas")
                itemView.tv_fase.text = "Fase Pendahuluan"

            itemView.btn_monitoring.setOnClickListener() {
                callback.onClick(monitoring)
            }
        }

    }
    interface Callback {
        fun onClick(data : Pesanan)
    }

    var listMonitoringAwal : List<Pesanan> = ArrayList()

    fun setData(data: ArrayList<Pesanan>){
        this.listMonitoringAwal = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMonitoringAwal = LayoutInflater.from(parent.context).inflate(R.layout.item_monitoring, parent, false)
        return MyViewHolder(viewMonitoringAwal,callback)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMonitoringAwal[position])
    }

    override fun getItemCount(): Int = listMonitoringAwal.size



}


