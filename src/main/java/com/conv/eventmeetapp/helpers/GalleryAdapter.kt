package com.conv.eventmeetapp.helpers



import com.conv.eventmeetapp.models.Event



import android.content.Context
import android.content.Intent


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.activities.PhotoViewer
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.gallery_card_view.view.*


class GalleryAdapter(val context:Context,val photos:List<String>) : RecyclerView.Adapter<GalleryAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.gallery_card_view,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val photo = photos[pos]
        holder.setData(photo,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current:String? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {

                var intent = Intent(this@GalleryAdapter.context,PhotoViewer::class.java)
                intent.putExtra("url",current);
                context.startActivity(intent)
            }
        }
        fun setData(p:String, pos: Int){
            Glide.with(context).load(p).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.iv_photo)
            current = p

        }
    }
}


