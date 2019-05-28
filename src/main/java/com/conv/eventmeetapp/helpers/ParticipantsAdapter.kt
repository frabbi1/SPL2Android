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
import com.conv.eventmeetapp.class_activities.ParticipantDetails
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.Event
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.models.ParticipantSupplier
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.participant_card_view.view.*

class ParticipantsAdapter(val context:Context,val participants:List<Participant>) : RecyclerView.Adapter<ParticipantsAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.participant_card_view,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val participant = participants[pos]
        holder.setData(participant,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current: Participant? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {


                //CurrentEvent.setEventDetails(current!!.id,current!!.name,current!!.location,current!!.start_date,current!!.end_date,current!!.description,current!!.longitude,current!!.latitude)
                ParticipantSupplier.setList(current!!.id,current!!.name,current!!.email,current!!.age,current!!.gender,
                    current!!.occupation.toString(),current!!.institution.toString(),current!!.phone,current!!.nationality.toString(),current!!.photo.toString() )
                var intent = Intent(this@ParticipantsAdapter.context, ParticipantDetails::class.java)
                context.startActivity(intent)
            }
        }
        fun setData(p:Participant, pos: Int){
            itemView.txvName.text = p.name
            Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.proPicCard)
            this.current = p
            this.curpos = pos
        }
    }
}