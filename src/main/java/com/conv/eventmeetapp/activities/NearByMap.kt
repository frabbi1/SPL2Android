package com.conv.eventmeetapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.CurrentEvent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NearByMap : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var lon = 0.0
    var lat = 0.0
    var loc = ""
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val plc = LatLng(lon, lat)
        val zoom = 18.0f
        mMap.addMarker(MarkerOptions().position(plc).title(loc))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plc,zoom))
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.nearbymap)

        var intent = intent
        lon = intent.getStringExtra("lon").toDouble()
        lat = intent.getStringExtra("lat").toDouble()
        loc = intent.getStringExtra("loc")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.near_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}
