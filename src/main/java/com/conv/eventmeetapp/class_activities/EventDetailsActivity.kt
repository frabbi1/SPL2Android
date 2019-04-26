package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.CurrentEvent
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        eventName.text = CurrentEvent.name
        eventLoc.text = CurrentEvent.location
        sdate.text = CurrentEvent.startDate
        edate.text = CurrentEvent.endDate

    }
}
