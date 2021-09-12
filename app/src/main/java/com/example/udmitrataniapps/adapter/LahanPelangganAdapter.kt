package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.model.pelanggan.DataLahan
import com.example.udmitrataniapps.pelanggan.lahanpelanggan.LahanPelangganActivity
import kotlinx.android.synthetic.main.item_lahan_pelanggan.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LahanPelangganAdapter(val callback : Callback) :RecyclerView.Adapter<LahanPelangganAdapter.MyViewHolder>() {
    var listLahan: List<DataLahan> = ArrayList()
    lateinit var sharedPref: PreferencesHelper

    fun setData(data : List<DataLahan>){
        this.listLahan = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LahanPelangganAdapter.MyViewHolder {
        val viewLahanPelanggan = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lahan_pelanggan, parent, false)
        return MyViewHolder(viewLahanPelanggan, callback)
    }

    override fun onBindViewHolder(holder: LahanPelangganAdapter.MyViewHolder, position: Int) {
        holder.bind(listLahan[position])
    }

    override fun getItemCount(): Int = listLahan.size

    inner class MyViewHolder(itemView: View, callback: Callback) : RecyclerView.ViewHolder(itemView) {
        fun bind(l: DataLahan) {
            itemView.tv_nama_lahan.text = l.nama_lahan
            itemView.tv_luas_lahan_pelanggan.text = l.luas_lahan.toString() + " hektar"
            itemView.tv_alamat_lahan_pelanggan.text = l.alamat

            itemView.btn_update_lahan.setOnClickListener {
                callback.onClick(l)
            }

            itemView.btn_delete_lahan.setOnClickListener {
                callback.onDelete(l)
            }
        }
    }

    interface Callback{
        fun onClick(data: DataLahan)
        fun onDelete(data: DataLahan)
    }

}