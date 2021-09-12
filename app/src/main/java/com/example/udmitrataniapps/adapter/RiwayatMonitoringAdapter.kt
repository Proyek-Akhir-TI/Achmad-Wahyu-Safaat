package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pelanggan.Pesanan
import kotlinx.android.synthetic.main.item_pesanans.view.*

class RiwayatMonitoringAdapter (val callback: Callback) :RecyclerView.Adapter<RiwayatMonitoringAdapter.PesanansViewHolder>() {
    var listPesanans : List<Pesanan> = ArrayList()

    fun setDataPesanans(data: List<Pesanan>){
        this.listPesanans = data
    }

    inner class PesanansViewHolder(itemView: View, callback: Callback): RecyclerView.ViewHolder(itemView) {
        fun bind(data: Pesanan) {
            itemView.tv_nama_lahan.text = data.nama_lahan
            itemView.tv_pesanan_nomor_induk.text = data.nomor_induk
            itemView.tv_pesanan_status_pesanan.text = data.status_pesanan
            itemView.tv_pesanan_aktivitas.text = data.created_at

            itemView.setOnClickListener {
                callback.onClick(data)
            }
        }

    }

    interface Callback {
        fun onClick(data: Pesanan)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesanansViewHolder {
        var viewPesanansPelanggan = LayoutInflater.from(parent.context).inflate(R.layout.item_pesanans, parent, false)
        return PesanansViewHolder(viewPesanansPelanggan, callback)
    }

    override fun onBindViewHolder(holder: PesanansViewHolder, position: Int) {
        holder.bind(listPesanans[position])

    }

    override fun getItemCount(): Int = listPesanans.size
}