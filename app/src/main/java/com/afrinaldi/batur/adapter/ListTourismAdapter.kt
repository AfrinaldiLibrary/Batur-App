package com.afrinaldi.batur.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.batur.R
import com.afrinaldi.batur.model.DataTourismModel

class ListTourismAdapter : RecyclerView.Adapter<ListTourismAdapter.ViewHolder>() {
    private val dataTourism = ArrayList<DataTourismModel>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun insertData(dataTourism: ArrayList<DataTourismModel>){
        this.dataTourism.clear()
        this.dataTourism.addAll(dataTourism)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tourism, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTourism[position])

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(dataTourism[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = dataTourism.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataTourismModel){
            itemView.findViewById<TextView>(R.id.tv_title).text = item.title
            itemView.findViewById<TextView>(R.id.tv_description).text = item.description
            itemView.findViewById<ImageView>(R.id.iv_image).setImageResource(item.image)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTourismModel)
    }
}