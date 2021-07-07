package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.pelanggan.VarietasPadi
import kotlinx.android.synthetic.main.item_benih.view.*

class VarietasBenihAdapter: RecyclerView.Adapter<VarietasBenihAdapter.MyViewHolder>() {

    var listBenih: List<VarietasPadi> = ArrayList()

    fun setDataBenih(data : List<VarietasPadi>) {
        this.listBenih = data
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(b: VarietasPadi){
            itemView.tv_title_benih.text = b.nama_varietas
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var dataBenihAdapter = LayoutInflater.from(parent.context).inflate(R.layout.item_benih, parent, false)
        return MyViewHolder(dataBenihAdapter)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listBenih[position])
    }

    override fun getItemCount(): Int {
        return listBenih.size
    }


}