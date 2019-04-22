package com.conv.eventmeetapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.join_event_fragment.view.*

class JoinEventFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =inflater.inflate(R.layout.join_event_fragment, container, false)
        view.btnJoin.setOnClickListener {
            var intent = Intent(this.context,MainFrame::class.java)
            startActivity(intent)
        }
        return view
    }


    companion object {
        fun newInstance(): JoinEventFragment = JoinEventFragment()
    }
}