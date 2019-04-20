package com.conv.eventmeetapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.RadioButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.conv.eventmeetapp.Models.Participant
import com.conv.eventmeetapp.Services.BackEndService
import com.conv.eventmeetapp.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_edit_profile.*
import retrofit2.Call
import retrofit2.Response


class InitialEditProfile : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    private var name = ""
    private var email = ""
    private var id = ""
    private var photo = ""
    private var age = ""
    private var occupation = ""
    private var institution = ""
    private var nationality = ""
    private var gender = ""
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        toolbar = supportActionBar!!
        toolbar.title = "Edit Proflie"

        var intent = intent
        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")
        id = intent.getStringExtra("id")
        photo = intent.getStringExtra("photo")
        Glide.with(this).load(photo).into(proPic)
        nameF.setText(name)


        save.setOnClickListener {
            saveInfo()

        }



    }
    fun saveInfo(){
        institution = insF.text.toString()
        occupation = occupationF.text.toString()
        phone = phoneF.text.toString()
        age = ageF.text.toString()
        name = nameF.text.toString()
        nationality = nationalityF.text.toString()

        var genderButton = findViewById<RadioButton>(radiogrp.checkedRadioButtonId)
        gender = genderButton.text.toString()
        Toast.makeText(this,gender,Toast.LENGTH_LONG).show()

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var newPaticipant = Participant(id, name, email, age, gender, occupation, institution, phone, nationality, photo)
        val requestCall = service.addParticipant(newPaticipant)


        requestCall.enqueue(object : retrofit2.Callback<Participant>{
            override fun onResponse(call: Call<Participant>, response: Response<Participant>){
                if(response.isSuccessful){
                    var temp = response.body()
                    val intent = Intent(this@InitialEditProfile, Navigation::class.java)
                    intent.putExtra("name",name)
                    intent.putExtra("email",email)
                    intent.putExtra("id",id)
                    intent.putExtra("photo",photo)
                    intent.putExtra("age",age)
                    intent.putExtra("occupation",occupation)
                    intent.putExtra("institution",institution)
                    intent.putExtra("phone",phone)
                    intent.putExtra("nationality",nationality)
                    intent.putExtra("gender",gender)

                    startActivity(intent)



                }else{

                    Toast.makeText(this@InitialEditProfile, "Failed to save information", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Participant>, t: Throwable) {
                finish()
                //Toast.makeText(this@InitialEditProfile, "Information save failed", Toast.LENGTH_SHORT)
                 //   .show()

            }
        })

    }
}
