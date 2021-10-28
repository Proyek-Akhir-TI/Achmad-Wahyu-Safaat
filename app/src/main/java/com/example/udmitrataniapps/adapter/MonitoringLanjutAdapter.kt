package com.example.udmitrataniapps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.MonitoringFase
import com.example.udmitrataniapps.model.pelanggan.PemeriksaanAwal
import com.example.udmitrataniapps.model.pelanggan.PemeriksaanLanjut
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.view.*
import kotlinx.android.synthetic.main.item_monitoring.view.*
import kotlinx.android.synthetic.main.item_riwayat_monitoring.view.*
import kotlinx.android.synthetic.main.item_riwayat_monitoring.view.isolasi_barat
import kotlinx.android.synthetic.main.item_riwayat_monitoring.view.isolasi_selatan
import kotlinx.android.synthetic.main.item_riwayat_monitoring.view.isolasi_timur
import kotlinx.android.synthetic.main.item_riwayat_monitoring.view.isolasi_utara

class MonitoringLanjutAdapter : RecyclerView.Adapter<MonitoringLanjutAdapter.MyViewHolder>() {
    var listMonitoring : List<PemeriksaanLanjut> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var viewMonitoring = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat_monitoring, parent, false)
        return MyViewHolder(viewMonitoring)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMonitoring[position])
    }

    override fun getItemCount(): Int {
        return  listMonitoring.size
    }

    fun setMonitoringLanjutData(data: List<PemeriksaanLanjut>){
        this.listMonitoring = data
    }

    inner class MyViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(m: PemeriksaanLanjut) {
            itemView.tv_title.text = "Hasil Pemeriksaan Fase " + m.jenis_pemeriksaan
            itemView.isolasi_timur.text = "Isolasi Timur : " + m.isolasi_timur
            itemView.isolasi_barat.text = "Isolasi Barat : " + m.isolasi_barat
            itemView.isolasi_selatan.text = "Isolasi Selatan : " + m.isolasi_selatan
            itemView.isolasi_utara.text = "Isolasi Utara : " + m.isolasi_utara
            itemView.barrier.text = "Barier : " + m.barier + " meter"
            itemView.tv_waktu.text = "Waktu : -"
            itemView.tv_sifat_penanaman.text = m.sifat_penanaman + "dengan sifat nya"
            itemView.tv_serangan_hama.text = m.serangan_hama
            itemView.tv_perkiraan_tgl_panen.text = "Perkiraan Tanggal Panen : "+m.perkiraan_tanggal_panen
            itemView.tv_perkiraan_produksi.text = "Perkiraan Produksi Panen : "+m.perkiraan_produksi+ " Kilogram"
            itemView.tv_contoh_pemeriksaan.text = "Sampel Pemeriksaan : "+m.jumlah_contoh_pemeriksaan +" rumpun"
            itemView.tv_cvl1.text = "CVL 1 = "+ if (m.inbrida_cvl1==null) 0 else m.inbrida_cvl2
            itemView.tv_cvl2.text = "CVL 2 = "+ if (m.inbrida_cvl4==null) 0 else m.inbrida_cvl2
            itemView.tv_cvl3.text = "CVL 3 = "+ if (m.inbrida_cvl3==null) 0 else m.inbrida_cvl3
            itemView.tv_cvl4.text = "CVL 4 = "+ if (m.inbrida_cvl4==null) 0 else m.inbrida_cvl4
//            val image = "http://192.168.1.107/proyek_akhir/public/storage/monitorings/"+m.foto_monitoring
            val image = "https://mitratani.store/public/storage/monitorings/"+m.foto_monitoring

            Picasso.get()
                .load(image)
                .error(R.drawable.no_image)
                .into(itemView.iv_foto_monitoring)

            itemView.tv_kesimpulan.text = ""+m.status_pemeriksaan
        }

    }

}