package com.conv.eventmeetapp.helpers

import com.conv.eventmeetapp.models.Event



import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.activities.MainFrame
import com.conv.eventmeetapp.models.CurrentEvent

import kotlinx.android.synthetic.main.my_event_cardview.view.*


class MyEventsAdapter(val context:Context,val events:List<Event>) : RecyclerView.Adapter<MyEventsAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.my_event_cardview,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val event = events[pos]
        holder.setData(event,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current:Event? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {

                CurrentEvent.setEventDetails(current!!.id,current!!.name,current!!.location,current!!.start_date,current!!.end_date,current!!.description,current!!.longitude,current!!.latitude)
                var intent = Intent(this@MyEventsAdapter.context, MainFrame::class.java)
                context.startActivity(intent)
            }
        }
        fun setData(e:Event, pos: Int){
            itemView.txvEventName.text = e.name
            this.current = e
            this.curpos = pos


        }
    }
}


