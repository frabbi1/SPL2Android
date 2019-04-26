package com.conv.eventmeetapp.services

import com.conv.eventmeetapp.models.CurrentUser
import com.conv.eventmeetapp.models.Event

import com.conv.eventmeetapp.models.Participant
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @POST("participants/add")
    fun addParticipant(@Body newParticipant: Participant): Call<Participant>

    @POST("event/register")
    fun register(@Query("e_id")eventID:Int, @Query("p_id")pid:String): Call<String>

    @PUT("participants/update/{id}")
    fun updateParticipant(@Path("id") id:String, @Body newParticipant: Participant): Call<Participant>

    @GET("participants/check/{id}")
    fun checkNewUser(@Path("id") id:String): Call<Participant>

    @GET("participants/{id}")
    fun getParticipant(@Path("id") id:String): Call<Participant>

    @GET("event/fetch")
    fun fetchEvent(@Query("id") id : Int, @Query("code") code:String): Call<Event>
}