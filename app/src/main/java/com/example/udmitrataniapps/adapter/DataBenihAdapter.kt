package com.example.udmitrataniapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.DataBenih
import kotlinx.android.synthetic.main.item_benih.view.*

class DataBenihAdapter: RecyclerView.Adapter<DataBenihAdapter.MyViewHolder>() {

    var dataBenih: List<DataBenih> = ArrayList()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(b: DataBenih){
            itemView.tv_title_benih.text = b.getBenih()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var dataBenihAdapter = LayoutInflater.from(parent.context).inflate(R.layout.item_benih, parent, false)
        return MyViewHolder(dataBenihAdapter)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataBenih[position])
    }

    override fun getItemCount(): Int {
        return dataBenih.size
    }

    fun setDataBenihRows(data: List<DataBenih>){
        this.dataBenih = data
    }
}