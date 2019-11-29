package com.example.alcatel_dasar_android.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tumbuhan.R


class Adapter(val list: ArrayList<Model>) :
    RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    private lateinit var onClickItem: OnClickItem

    fun setItemClicked(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tumbuhan, parent, false)
        return AdapterViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val tumbuhan = list[position]
        Glide.with(holder.itemView.context)
            .load(tumbuhan.imageUrl)
            .error(R.drawable.ic_broken_image_black_24dp)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = "${tumbuhan.name} ( ${tumbuhan.plantId} )"
        holder.tvDetail.text = tumbuhan.description

        holder.itemView.setOnClickListener { onClickItem.onItemClicked(list[holder.adapterPosition]) }

    }


    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnClickItem {
        fun onItemClicked(data: Model)
    }
}