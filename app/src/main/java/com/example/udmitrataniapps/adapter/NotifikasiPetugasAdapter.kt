package com.example.udmitrataniapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.Notifikasi
import kotlinx.android.synthetic.main.item_notifikasi.view.*

class NotifikasiPetugasAdapter() : RecyclerView.Adapter<NotifikasiPetugasAdapter.NotifikasiPetugasViewHolder>() {

    var notifikasi : List<Notifikasi> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifikasiPetugasAdapter.NotifikasiPetugasViewHolder {
        var viewNotifikasi = LayoutInflater.from(parent.context).inflate(R.layout.item_notifikasi, parent, false)
        return NotifikasiPetugasViewHolder(viewNotifikasi)
    }

    override fun onBindViewHolder(holder: NotifikasiPetugasAdapter.NotifikasiPetugasViewHolder, position: Int) {
        holder.bind(notifikasi[position])
    }

    override fun getItemCount(): Int {
        return notifikasi.size
    }

    fun setNotifikasiPetugas(data: List<Notifikasi>){
        this.notifikasi = data
    }

    inner class NotifikasiPetugasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(n: Notifikasi){
            itemView.tv_title.text = n.getTitle()
            itemView.tv_desc.text = n.getDesc()
        }
    }

}