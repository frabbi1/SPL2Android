package com.conv.eventmeetapp.activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.class_activities.*
import kotlinx.android.synthetic.main.activity_main_frame.*

class MainFrame : AppCompatActivity() {
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_frame)


        //getParticipantList()

        btnSwitchOut.setOnClickListener {
            //var intent = Intent(this, Navigation::class.java)
            //startActivity(intent)
            finish()

        }
        btnCheckIn.setOnClickListener {
            Toast.makeText(this, "Long Press", Toast.LENGTH_SHORT).show()
        }
        btnCheckIn.setOnLongClickListener {
            if(btnCheckIn.text.toString().toLowerCase() == "checked in"){
                btnCheckIn.text = "CHECK IN"
                btnCheckIn.setBackgroundColor(Color.argb(255,93,139,88))

            }
            else{
                btnCheckIn.text = "CHECKED IN"
                btnCheckIn.setBackgroundColor(Color.BLUE)

            }
            true
        }
        btnEvent.setOnClickListener {
            var intent2 = Intent(this, EventDetailsActivity::class.java)
            startActivity(intent2)

        }
        btnLocation.setOnClickListener {
            var intent2 = Intent(this, LocationActivity::class.java)
            startActivity(intent2)
        }
        btnParticipant.setOnClickListener {
            var intent2 = Intent(this, ParticipantListActivity::class.java)
            startActivity(intent2)
        }
        btnNotification.setOnClickListener{
            var intent2 = Intent(this, Notification::class.java)
            startActivity(intent2)
        }
        btnPhoto.setOnClickListener{
            var intent2 = Intent(this, Gallery::class.java)
            startActivity(intent2)
        }


    }
}
