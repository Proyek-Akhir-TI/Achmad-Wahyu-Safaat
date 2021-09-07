package com.example.udmitrataniapps.petugas.fragment.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pegawai.JadwalMonitoring
import com.example.udmitrataniapps.model.pegawai.ResponseJadwalMonitoring
import kotlinx.android.synthetic.main.item_jadwal.view.*
import kotlinx.android.synthetic.main.item_lahan_pelanggan.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class JadwalFaseVegetatifFragmentAdapter() : RecyclerView.Adapter<JadwalFaseVegetatifFragmentAdapter.ViewHolder> (){

    var jadwalMonitoring : List<JadwalMonitoring> = ArrayList()

    fun setData(data : List<JadwalMonitoring>){
        this.jadwalMonitoring = data
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: JadwalMonitoring) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val convert = dateFormat.parse(data.fase_vegetatif)!!
            dateFormat.applyPattern("dd-MM-yyyy")
            val newDateFormat = dateFormat.format(convert)

            itemView.tv_phone.text = data.pesanan.lahan_pelanggan.pelanggan.telepon
            itemView.tv_nama_pemilik.text = data.pesanan.lahan_pelanggan.pelanggan.nama_lengkap
            itemView.tv_lokasi.text = "Alamat : " + data.pesanan.lahan_pelanggan.alamat
            itemView.tv_tanggal.text = newDateFormat
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JadwalFaseVegetatifFragmentAdapter.ViewHolder {
        val viewJadwalMonitoring = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(viewJadwalMonitoring)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: JadwalFaseVegetatifFragmentAdapter.ViewHolder, position: Int) {
        holder.bind(jadwalMonitoring[position])
    }

    override fun getItemCount(): Int = jadwalMonitoring.size
}
