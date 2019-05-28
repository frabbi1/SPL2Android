package com.conv.eventmeetapp.helpers

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.activities.MainFrame
import com.conv.eventmeetapp.activities.NearByMap
import com.conv.eventmeetapp.class_activities.ParticipantDetails
import com.conv.eventmeetapp.models.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.naerme_card_view.view.*
import kotlinx.android.synthetic.main.participant_card_view.view.*

class NearMeAdapter(val context:Context,val places:List<Place>) : RecyclerView.Adapter<NearMeAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.naerme_card_view,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val place = places[pos]
        holder.setData(place,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current: Place? = null
        var curpos = 0
        init {
            itemView.btnShow.setOnClickListener {


                var intent = Intent(this@NearMeAdapter.context, NearByMap::class.java)
                intent.putExtra("lon", current!!.lon)
                intent.putExtra("lat", current!!.lat)
                intent.putExtra("loc", current!!.loc)
                context.startActivity(intent)
            }
        }
        fun setData(p:Place, pos: Int){
            itemView.pname.text = p.loc
            //Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            //Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.proPicCard)
            this.current = p
            this.curpos = pos
        }
    }
}