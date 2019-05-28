package com.conv.eventmeetapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.helpers.MyEventsAdapter
import com.conv.eventmeetapp.helpers.ParticipantsAdapter
import com.conv.eventmeetapp.models.CurrentUser
import com.conv.eventmeetapp.models.Event
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.models.ParticipantSupplier
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_participant_list.*
import kotlinx.android.synthetic.main.event_fragment.*
import retrofit2.Call
import retrofit2.Response

class EventsFragmnet : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.event_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lm = LinearLayoutManager(this@EventsFragmnet.context)
        lm.orientation = LinearLayoutManager.VERTICAL

        e_recView.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var rcall = service.getJoinedEvents(CurrentUser.getUser())
        rcall.enqueue(object : retrofit2.Callback<List<Event>>{
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>){
                if(response.isSuccessful){
                    var e = response.body()!!
                    //ParticipantSupplier.setList(response.body()!!)
                    val adapter =  MyEventsAdapter(this@EventsFragmnet.context!!, e)
                    e_recView.adapter = adapter
                    Toast.makeText(this@EventsFragmnet.context!!, "hoise", Toast.LENGTH_SHORT)
                        .show()



                }else{
                    Toast.makeText(this@EventsFragmnet.context!!, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {

                Toast.makeText(this@EventsFragmnet.context!!, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }









    companion object {
        fun newInstance(): EventsFragmnet =
            EventsFragmnet()
    }
}