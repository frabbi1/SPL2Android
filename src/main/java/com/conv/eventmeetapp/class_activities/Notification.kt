package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.helpers.NearMeAdapter
import com.conv.eventmeetapp.helpers.NotificationAdapter
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.Place
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_near_me.*
import kotlinx.android.synthetic.main.activity_notification.*
import retrofit2.Call
import retrofit2.Response

class Notification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        var lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        not_rec.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getNotification(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  NotificationAdapter(this@Notification, p)
                    not_rec.adapter = adapter
                    //Toast.makeText(this@Notification, "hoise", Toast.LENGTH_SHORT)
                      //  .show()



                }else{
                    Toast.makeText(this@Notification, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                finish()
                Toast.makeText(this@Notification, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })


    }
}
