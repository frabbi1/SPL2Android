package com.conv.eventmeetapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        toolbar = supportActionBar!!
        toolbar.title = "Edit Proflie"
        Glide.with(this).load(R.drawable.back).into(proPic)

        save.setOnClickListener {
            saveInfo()
        }



    }
    fun saveInfo(){
        finish()
    }
}
