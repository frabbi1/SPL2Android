package com.conv.eventmeetapp.Services

import com.conv.eventmeetapp.Models.Participant
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @POST("participants/add")
    fun addParticipant(@Body newParticipant: Participant): Call<Participant>

    @GET("participants/check/{id}")
    fun checkNewUser(@Path("id") id:String): Call<Participant>
}