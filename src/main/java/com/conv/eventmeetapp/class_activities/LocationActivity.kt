package com.conv.eventmeetapp.class_activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.CurrentEvent

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(CurrentEvent.longitude.toDouble(), CurrentEvent.latitude.toDouble())
        val zoom = 18.0f
        mMap.addMarker(MarkerOptions().position(sydney).title(CurrentEvent.location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoom))
    }
}
