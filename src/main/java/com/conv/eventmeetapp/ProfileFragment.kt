package com.conv.eventmeetapp
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.RequestOptions

import com.google.android.gms.auth.api.signin.GoogleSignInClient

import kotlinx.android.synthetic.main.profile_fragment.view.*

@GlideModule
class ProfileFragment : Fragment(){
    var mGoogleSignInClient: GoogleSignInClient? = null
    lateinit var profile : Profile
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.profile_fragment, container, false)
        var parent = activity as Navigation
        profile = parent.getProfileData()

        Glide.with(this).load(profile.photo).apply(RequestOptions.overrideOf(250,200))
            .error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(view.proPic);
        view.nameField.setText(profile.name)
        view.emailField.setText(profile.email)
        view.gender.setText(profile.gender)
        view.ageField.setText(profile.age)
        view.occ.setText(profile.occupation)
        view.nat.setText(profile.nationality)
        view.insField.setText(profile.institution)
        view.phoneField.setText(profile.phone)

        view.editProfile.setOnClickListener {
            var intent = Intent(this.context, EditProfile::class.java)
            intent.putExtra("name",profile.name)
            intent.putExtra("email",profile.email)
            intent.putExtra("id",profile.id)
            intent.putExtra("photo",profile.photo)
            intent.putExtra("occupation",profile.occupation)
            intent.putExtra("gender",profile.gender)
            intent.putExtra("institution",profile.institution)
            intent.putExtra("age",profile.age)
            intent.putExtra("phone",profile.phone)
            intent.putExtra("nationality",profile.nationality)
            startActivity(intent)
        }
        return view
    }





    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}