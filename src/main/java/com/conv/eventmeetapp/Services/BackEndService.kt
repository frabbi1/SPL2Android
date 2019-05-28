package com.conv.eventmeetapp.services

import com.conv.eventmeetapp.models.*

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

    @GET("participants/all/{id}")
    fun getAllParticipants(@Path("id" )id:String):Call<List<Participant>>
    @GET("event/joined/{id}")
    fun getJoinedEvents(@Path("id" )id:String):Call<List<Event>>

    @GET("event/fetch")
    fun fetchEvent(@Query("id") id : Int, @Query("code") code:String): Call<Event>

    @GET("file/photos/{id}")
    fun getPhoto(@Path("id" )id:String): Call<List<String>>

    @GET("file/resources/{id}")
    fun getFile(@Path("id" )id:String): Call<List<File>>

    @GET("event/nearme/{id}")
    fun getPlace(@Path("id" )id:String): Call<List<Place>>

    @GET("event/notification/{id}")
    fun getNotification(@Path("id" )id:String): Call<List<String>>

}