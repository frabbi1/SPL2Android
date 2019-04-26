package com.conv.eventmeetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.conv.eventmeetapp.activities.MainFrame
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.CurrentUser

import com.conv.eventmeetapp.models.Event

import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.join_event_fragment.*
import kotlinx.android.synthetic.main.join_event_fragment.view.*
import retrofit2.Call
import retrofit2.Response

class JoinEventFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =inflater.inflate(R.layout.join_event_fragment, container, false)
        view.btnJoin.setOnClickListener {
            var idTemp = eventID.text.toString()
            var id = 0
            if(idTemp=="") id = 0
            else id = idTemp.toInt()
            var code = eventCode.text.toString()
            val service = ServiceBuilder.buildService(BackEndService::class.java)
            val requestCall = service.fetchEvent(id,code)
            requestCall.enqueue(object : retrofit2.Callback<Event>{
                override fun onFailure(call: Call<Event>, t: Throwable) {
                    Toast.makeText(this@JoinEventFragment.context, "Problem hoise", Toast.LENGTH_SHORT).show()
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Event>, response: Response<Event>){
                    if(response.isSuccessful){
                        //Toast.makeText(this@JoinEventFragment.context, response.body()?.name,Toast.LENGTH_SHORT).show()
                        var result = response.body()!!
                        var id = result.id
                        var name = result.name
                        var location = result.location
                        var startDate = result.start_date
                        var endDate = result.end_date
                        CurrentEvent.setEventDetails(id,name,location,startDate,endDate)
                        registerParticipant()
                        var intent = Intent(this@JoinEventFragment.context, MainFrame::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@JoinEventFragment.context, "Invalid EventID or EventCode",Toast.LENGTH_SHORT).show()
                    }
                }


            })
            //var intent = Intent(this.context, MainFrame::class.java)
            //startActivity(intent)
        }
        return view
    }

    fun registerParticipant(){
        val  s  = ServiceBuilder.buildService((BackEndService::class.java))
        val rCall = s.register(CurrentEvent.id,CurrentUser.getUser())

        rCall.enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    return
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //Toast.makeText(this@JoinEventFragment.context,"Failed to Insert",Toast.LENGTH_SHORT).show()
            }


        })

    }



    companion object {
        fun newInstance(): JoinEventFragment =
            JoinEventFragment()
    }
}