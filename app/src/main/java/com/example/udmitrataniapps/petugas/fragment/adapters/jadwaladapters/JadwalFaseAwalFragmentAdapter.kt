package com.example.udmitrataniapps.petugas.fragment.adapters.jadwaladapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pegawai.JadwalMonitoring
import kotlinx.android.synthetic.main.item_jadwal.view.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList


class JadwalFaseAwalFragmentAdapter() : RecyclerView.Adapter<JadwalFaseAwalFragmentAdapter.ViewHolder> (){

    var jadwalMonitoring : List<JadwalMonitoring> = ArrayList()

    fun setData(data : List<JadwalMonitoring>){
        this.jadwalMonitoring = data
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: JadwalMonitoring) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val convert = dateFormat.parse(data.fase_pendahuluan)!!
            dateFormat.applyPattern("dd-MM-yyyy")
            val newDateFormat = dateFormat.format(convert)

            itemView.tv_phone.text = data.pesanan.lahan_pelanggan.pelanggan.telepon ?: "informasi tidak Tersedia"
            itemView.tv_nama_pemilik.text = data.pesanan.lahan_pelanggan.pelanggan.nama_lengkap ?: "informasi tidak Tersedia"
            itemView.tv_lokasi.text = "Alamat : " + data.pesanan.lahan_pelanggan.alamat ?: "informasi tidak Tersedia"
            itemView.tv_tanggal.text = newDateFormat
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewJadwalMonitoring = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(viewJadwalMonitoring)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jadwalMonitoring[position])
    }

    override fun getItemCount(): Int = jadwalMonitoring.size
}
