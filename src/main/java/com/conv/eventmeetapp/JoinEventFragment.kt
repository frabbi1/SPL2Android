package com.conv.eventmeetapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class JoinEventFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val x =inflater.inflate(R.layout.join_event_fragment, container, false)
        return x
    }


    companion object {
        fun newInstance(): JoinEventFragment = JoinEventFragment()
    }
}