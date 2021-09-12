package com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pegawai.Pesanan
import kotlinx.android.synthetic.main.item_monitoring.view.*

class MonitoringBerbungaAdapter(val callback: Callback) : RecyclerView.Adapter<MonitoringBerbungaAdapter.MyViewHolder>() {
    var listMonitoring : List<Pesanan> = ArrayList()

    fun setData(data: ArrayList<Pesanan>){
        this.listMonitoring = data
    }
    inner class MyViewHolder(itemView: View, callback: Callback) : RecyclerView.ViewHolder(itemView) {
        fun bind(monitoring: Pesanan) {
            itemView.tv_nama_pemilik.text = monitoring.lahan_pelanggan.pelanggan.nama_lengkap ?: "Lahan Tidak Tersedia di daftar lahan pelanggan"
            itemView.tv_nomor_induk.text = monitoring.nomor_induk ?: "Nomor Induk Belum Tesedia"
            itemView.tv_lokasi.text = monitoring.lahan_pelanggan.alamat
            if (monitoring.status_pesanan == "Fase Vegetatif")
                itemView.tv_fase.text = "Fase Berbunga"

            itemView.btn_monitoring.setOnClickListener() {
                callback.onClick(monitoring)
            }
        }

    }

    interface Callback {
        fun onClick(data : Pesanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMonitoringVegetatif = LayoutInflater.from(parent.context).inflate(R.layout.item_monitoring, parent, false)
        return MyViewHolder(viewMonitoringVegetatif, callback)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMonitoring[position])
    }

    override fun getItemCount(): Int = listMonitoring.size
}