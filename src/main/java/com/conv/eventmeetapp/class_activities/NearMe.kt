package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.helpers.NearMeAdapter
import com.conv.eventmeetapp.helpers.ParticipantsAdapter
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.models.Place
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_near_me.*
import kotlinx.android.synthetic.main.activity_participant_list.*
import retrofit2.Call
import retrofit2.Response

class NearMe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_near_me)

        var lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        near_rec.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getPlace(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<Place>>{
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  NearMeAdapter(this@NearMe, p)
                    near_rec.adapter = adapter
                    //Toast.makeText(this@NearMe, "hoise", Toast.LENGTH_SHORT)
                       // .show()



                }else{
                    Toast.makeText(this@NearMe, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                finish()
                Toast.makeText(this@NearMe, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })



    }
}
