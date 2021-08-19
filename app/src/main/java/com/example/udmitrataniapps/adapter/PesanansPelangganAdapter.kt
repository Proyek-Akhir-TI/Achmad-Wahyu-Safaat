package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pelanggan.Pesanan
import kotlinx.android.synthetic.main.item_pesanans.view.*
import kotlinx.android.synthetic.main.item_pesanans.view.tv_nama_lahan

class PesanansPelangganAdapter(val callback: Callback) : RecyclerView.Adapter<PesanansPelangganAdapter.PesanansViewHolder>() {
    var listPesanans : List<Pesanan> = ArrayList()

    fun setDataPesanans(data: List<Pesanan>){
        this.listPesanans = data
    }

    inner class PesanansViewHolder(itemView: View, callback: Callback): RecyclerView.ViewHolder(itemView) {
        fun bind(pesanan: Pesanan) {
            itemView.tv_nama_lahan.text = pesanan.nama_lahan
            itemView.tv_pesanan_nomor_induk.text = pesanan.nomor_induk ?: "Pesanan Belum Terkonfirmasi"
            itemView.tv_pesanan_status_pesanan.text = pesanan.status_pesanan
            itemView.tv_pesanan_aktivitas.text = pesanan.created_at

            itemView.setOnClickListener {
                callback.onClick(pesanan)
            }
        }

    }

    interface Callback {
        fun onClick(pesanan: Pesanan)
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