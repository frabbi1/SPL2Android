package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.helpers.FileAdapter
import com.conv.eventmeetapp.helpers.GalleryAdapter
import com.conv.eventmeetapp.helpers.ParticipantsAdapter
import com.conv.eventmeetapp.models.CurrentEvent
import com.conv.eventmeetapp.models.File
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_files.*
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.activity_participant_list.*
import retrofit2.Call
import retrofit2.Response

class Files : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)

        var lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        file_rec.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getFile(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<File>>{
            override fun onResponse(call: Call<List<File>>, response: Response<List<File>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    //var x = listOf<String>("http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506997download.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506994bg3.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1558969922329details.png")
                    val adapter =  FileAdapter(this@Files, p)
                    file_rec.adapter = adapter
                    Toast.makeText(this@Files, "hoise", Toast.LENGTH_SHORT)
                        .show()



                }else{
                    Toast.makeText(this@Files, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<File>>, t: Throwable) {
                //finish()
                Toast.makeText(this@Files, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }
}
