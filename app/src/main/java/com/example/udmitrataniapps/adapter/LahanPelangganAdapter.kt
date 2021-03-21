package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.LahanPelanggan
import kotlinx.android.synthetic.main.item_lahan_pelanggan.view.*

class LahanPelangganAdapter:RecyclerView.Adapter<LahanPelangganAdapter.MyViewHolder>() {

    var lahanPelanggan: List<LahanPelanggan> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LahanPelangganAdapter.MyViewHolder {
        var viewLahanPelanggan = LayoutInflater.from(parent.context).inflate(R.layout.item_lahan_pelanggan, parent, false)
        return MyViewHolder(viewLahanPelanggan)
    }

    override fun onBindViewHolder(holder: LahanPelangganAdapter.MyViewHolder, position: Int) {
        holder.bind(lahanPelanggan[position])
    }

    override fun getItemCount(): Int {
        return lahanPelanggan.size
    }

    fun setDataLahanPelanggan(data: List<LahanPelanggan>){
        this.lahanPelanggan = data
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(l: LahanPelanggan){
            itemView.tv_nama_lahan.text = l.getNamaLahan()
            itemView.tv_luas_lahan_pelanggan.text = l.getLuasLahan()
            itemView.tv_alamat_lahan_pelanggan.text = l.getAlamatLahan()
        }
    }
}