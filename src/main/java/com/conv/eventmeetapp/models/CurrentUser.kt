package com.conv.eventmeetapp.models

object CurrentUser {
    private var id:String = ""

    fun getUser():String{
        return id;
    }

    fun setUser(id:String){
        this.id = id
    }
}