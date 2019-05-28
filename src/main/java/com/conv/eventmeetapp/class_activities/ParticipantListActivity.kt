package com.conv.eventmeetapp.class_activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.activities.InitialEditProfile
import com.conv.eventmeetapp.activities.Navigation
import com.conv.eventmeetapp.helpers.ParticipantsAdapter
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.CurrentUser
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.models.ParticipantSupplier
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_participant_list.*
import retrofit2.Call
import retrofit2.Response

class ParticipantListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_list)

        var lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        p_recView.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getAllParticipants(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<Participant>>{
            override fun onResponse(call: Call<List<Participant>>, response: Response<List<Participant>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  ParticipantsAdapter(this@ParticipantListActivity, p)
                    p_recView.adapter = adapter
                    //Toast.makeText(this@ParticipantListActivity, "hoise", Toast.LENGTH_SHORT)
                     //   .show()



                }else{
                    Toast.makeText(this@ParticipantListActivity, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Participant>>, t: Throwable) {
                finish()
                Toast.makeText(this@ParticipantListActivity, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })






    }
}
