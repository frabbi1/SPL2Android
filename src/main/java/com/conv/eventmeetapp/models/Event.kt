package com.conv.eventmeetapp.models

data class Event (
    val id:Int,
    val name:String,
    val location:String,
    val start_date:String,
    val end_date:String,
    val description:String,
    val longitude:String,
    val latitude:String,
    val code:String
)

