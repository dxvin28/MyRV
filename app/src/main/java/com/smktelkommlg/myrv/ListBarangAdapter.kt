package com.smktelkommlg.myrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListBarangAdapter(val listBarang: ArrayList<Barang>) : RecyclerView.Adapter<ListBarangAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list, viewGroup, false)
        return ListViewHolder(view)}


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, from, photo) = listBarang[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvFrom.text = from
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listBarang[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int {
        return listBarang.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Barang)
    }

}
