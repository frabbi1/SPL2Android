package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.ParticipantSupplier
import kotlinx.android.synthetic.main.activity_participant_details.*

class ParticipantDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_details)

        nameField.text = ParticipantSupplier.name
        emailField.text = ParticipantSupplier.email
        gender.text = ParticipantSupplier.gender
        ageField.text = ParticipantSupplier.age
        occ.text = ParticipantSupplier.occupation
        nat.text = ParticipantSupplier.nationality
        insField.text = ParticipantSupplier.institution
        phoneField.text = ParticipantSupplier.phone
        Glide.with(this).load(ParticipantSupplier.photo).apply(RequestOptions.overrideOf(250,200))
            .error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(proPic)
    }
}
