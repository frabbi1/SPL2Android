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


class EditProfile : AppCompatActivity() {
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
        age = intent.getStringExtra("age")
        occupation = intent.getStringExtra("occupation")
        institution = intent.getStringExtra("institution")
        phone = intent.getStringExtra("phone")
        nationality = intent.getStringExtra("nationality")
        gender = intent.getStringExtra("gender")

        nameF.setText(name)
        ageF.setText(age)
        phoneF.setText(phone)
        Glide.with(this).load(photo).error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(proPic)
        occupationF.setText(occupation)
        insF.setText(institution)
        nationalityF.setText(nationality)
        radiogrp.check(male.id)


        save.setOnClickListener {
            saveInfo()

        }

    }
    fun saveInfo(){
        institution = insF.text.toString()
        occupation = occupationF.text.toString()
        phone = phoneF.text.toString()
        name = nameF.text.toString()
        age = ageF.text.toString()
        nationality = nationalityF.text.toString()

        var genderButton = findViewById<RadioButton>(radiogrp.checkedRadioButtonId)
        gender = genderButton.text.toString()

        if(name.trim().isEmpty() || age.trim().isEmpty()
            || gender.trim().isEmpty() || phone.trim().isEmpty()){


            Toast.makeText(this, "FIll the required field", Toast.LENGTH_LONG).show()
            return
        }

        //Toast.makeText(this,gender,Toast.LENGTH_LONG).show()

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var newParticipant = Participant(id, name, email, age, gender, occupation, institution, phone, nationality, photo)

        val requestCall = service.updateParticipant(id,newParticipant)
        requestCall.enqueue(object : retrofit2.Callback<Participant>{
            override fun onResponse(call: Call<Participant>, response: Response<Participant>){
                if(response.isSuccessful){
                    var temp = response.body()
                    val intent = Intent(this@EditProfile, Navigation::class.java)
                    intent.putExtra("id",temp!!.id)
                    startActivity(intent)
                    finish()



                }else{

                    Toast.makeText(this@EditProfile, "Failed to edit information", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Participant>, t: Throwable) {
                finish()
                Toast.makeText(this@EditProfile, "Information edit failed", Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }
}
