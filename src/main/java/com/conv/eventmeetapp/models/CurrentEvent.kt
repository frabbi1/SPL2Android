package com.conv.eventmeetapp.models

object CurrentEvent {

    var id = 0
    var name = ""
    var location = ""
    var startDate = ""
    var endDate = ""


    fun setEventDetails(
        id:Int,
        name:String,
        location:String,
        startDate:String,
        endDate:String

    ){
        this.id = id
        this.name = name
        this.location = location
        this.startDate = startDate
        this.endDate = endDate

    }

}