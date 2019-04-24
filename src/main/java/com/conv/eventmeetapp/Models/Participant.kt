package com.conv.eventmeetapp.models

data class Participant(
    var id : String,
    var name : String,
    var email : String,
    var age : String,
    var gender : String,
    var occupation : String?,
    var institution : String?,
    var phone : String,
    var nationality : String?,
    var photo : String?
)