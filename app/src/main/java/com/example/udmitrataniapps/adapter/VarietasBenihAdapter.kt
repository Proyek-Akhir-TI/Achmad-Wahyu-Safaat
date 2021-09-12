package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi
import kotlinx.android.synthetic.main.item_benih.view.*

class VarietasBenihAdapter(val callback: Callback): RecyclerView.Adapter<VarietasBenihAdapter.MyViewHolder>() {

    var listBenih: List<VarietasPadi> = ArrayList()

    fun setDataBenih(data : List<VarietasPadi>) {
        this.listBenih = data
    }
    inner class MyViewHolder(itemView: View, callback: Callback):RecyclerView.ViewHolder(itemView) {
        fun bind(b: VarietasPadi){
            itemView.tv_title_benih.text = b.nama_varietas

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


