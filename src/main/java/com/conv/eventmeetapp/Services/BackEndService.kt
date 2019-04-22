package com.conv.eventmeetapp.Services

import com.conv.eventmeetapp.Models.Participant
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @POST("participants/add")
    fun addParticipant(@Body newParticipant: Participant): Call<Participant>

    @PUT("participants/update/{id}")
    fun updateParticipant(@Path("id") id:String, @Body newParticipant: Participant): Call<Participant>

    @GET("participants/check/{id}")
    fun checkNewUser(@Path("id") id:String): Call<Participant>

    @GET("participants/{id}")
    fun getParticipant(@Path("id") id:String): Call<Participant>
}