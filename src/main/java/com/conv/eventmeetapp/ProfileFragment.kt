package com.conv.eventmeetapp
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*


class ProfileFragment : Fragment(){
    var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.profile_fragment, container, false)
        var parent = activity as Navigation
        var profile = parent.getProfileData()
        //Glide.with(this).load("http://www.ssaurel.com/tmp/logo_ssaurel.png").into(view.proPic);
        var img = view.proPic
        Picasso.get().load("http://www.ssaurel.com/tmp/logo_ssaurel.png").fit().into(img);

        view.nameField.setText(profile.name)

        return view
    }





    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}