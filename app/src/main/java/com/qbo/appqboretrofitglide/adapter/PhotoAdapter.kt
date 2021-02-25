package com.qbo.appqboretrofitglide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qbo.appqboretrofitglide.R
import com.qbo.appqboretrofitglide.model.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(private val lstPhoto : List<Photo>)
    : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvtitulophoto: TextView = itemView.findViewById(R.id.tvtitulophoto)
        val ivphoto: ImageView = itemView.findViewById(R.id.ivphoto)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
                layoutInflater.inflate(R.layout.item_photo,
                        parent, false)
        )
    }
    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        val item = lstPhoto[position]
        holder.tvtitulophoto.text =item.title
        Picasso.get().load(item.url).into(holder.ivphoto)
    }
    override fun getItemCount(): Int {
        return lstPhoto.size
    }
}