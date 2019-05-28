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

object ParticipantSupplier {
    var id : String =""
    var name : String =""
    var email : String=""
    var age : String=""
    var gender : String=""
    var occupation : String=""
    var institution : String=""
    var phone : String=""
    var nationality : String=""
    var photo : String=""

    fun setList(id:String,name : String,email : String,age : String,gender : String,occupation : String,institution : String,phone : String,nationality : String,photo : String){
        this.id = id
        this.name = name
        this.email = email
        this.age = age
        this.gender = gender
        this.occupation = occupation
        this.institution = institution
        this.phone = phone
        this.nationality = nationality
        this.photo = photo

    }
}