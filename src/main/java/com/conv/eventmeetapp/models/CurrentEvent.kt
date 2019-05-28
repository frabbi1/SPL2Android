package com.conv.eventmeetapp.models

object CurrentEvent {

    var id = 0
    var name = ""
    var location = ""
    var startDate = ""
    var endDate = ""
    var description = ""
    var longitude:String = ""
    var latitude:String = ""


    fun setEventDetails(
        id:Int,
        name:String,
        location:String,
        startDate:String,
        endDate:String,
        description:String,
        longitude:String,
        latitude:String

    ){
        this.id = id
        this.name = name
        this.location = location
        this.startDate = startDate
        this.endDate = endDate
        this.description = description
        this.longitude = longitude
        this.latitude = latitude

    }

}