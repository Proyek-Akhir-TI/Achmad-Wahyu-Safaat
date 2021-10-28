package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_benih.view.*

class VarietasBenihAdapter(val callback: Callback): RecyclerView.Adapter<VarietasBenihAdapter.MyViewHolder>() {

    var listBenih: List<VarietasPadi> = ArrayList()

    fun setDataBenih(data : List<VarietasPadi>) {
        this.listBenih = data
    }
    inner class MyViewHolder(itemView: View, callback: Callback):RecyclerView.ViewHolder(itemView) {
        fun bind(b: VarietasPadi){
            itemView.tv_title_benih.text = b.nama_varietas
//            val image = "http://192.168.1.107/proyek_akhir/public/images/"+b.foto_varietas
            val image = "https://mitratani.store/public/images/"+b.foto_varietas
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.calendar)
                .error(R.drawable.calendar)
                .into(itemView.iv_icon_benih)
            itemView.setOnClickListener {
                callback.onClick(b)
            }
        }
    }

    interface Callback {
        fun onClick(data : VarietasPadi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var dataBenihAdapter = LayoutInflater.from(parent.context).inflate(R.layout.item_benih, parent, false)
        return MyViewHolder(dataBenihAdapter,callback)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listBenih[position])
    }

    override fun getItemCount(): Int {
        return listBenih.size
    }

}


