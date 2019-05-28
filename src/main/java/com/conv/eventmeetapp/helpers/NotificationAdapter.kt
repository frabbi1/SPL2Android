package com.conv.eventmeetapp.helpers

import kotlinx.android.synthetic.main.notification_card_view.view.*

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.conv.eventmeetapp.R

import kotlinx.android.synthetic.main.naerme_card_view.view.*


class NotificationAdapter(val context:Context,val notifs:List<String>) : RecyclerView.Adapter<NotificationAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notification_card_view,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notifs.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val n = notifs[pos]
        holder.setData(n,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current: String? = null
        var curpos = 0

        fun setData(p:String, pos: Int){
            itemView.txtNot.text = p
            //Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            //Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.proPicCard)
            this.current = p
            this.curpos = pos
        }
    }
}