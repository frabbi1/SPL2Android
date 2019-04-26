package com.conv.eventmeetapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.navigation.*
import android.os.StrictMode
import com.conv.eventmeetapp.fragments.EventsFragmnet
import com.conv.eventmeetapp.fragments.JoinEventFragment
import com.conv.eventmeetapp.fragments.ProfileFragment
import com.conv.eventmeetapp.R




class Navigation : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var intent = intent!!

        id = intent.getStringExtra("id")

        toolbar = supportActionBar!!
        toolbar.title = "Profile"
        val profileFragment = ProfileFragment.newInstance()
        openFragment(profileFragment)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.navProfile) {
                toolbar.title = "Profile"


                openFragment(profileFragment)
                return@setOnNavigationItemSelectedListener true
            } else if (item.itemId == R.id.navEvents) {
                toolbar.title = "My Events"

                val eventFragment = EventsFragmnet.newInstance()
                openFragment(eventFragment)
                return@setOnNavigationItemSelectedListener true
            } else if (item.itemId == R.id.navJoinEvent){
                toolbar.title = "Join New Event"

                val joinNew = JoinEventFragment.newInstance()
                openFragment(joinNew)
                return@setOnNavigationItemSelectedListener true

            }
            else{
                signout()
                //finish()
                var intent = Intent(this, AuthenticationActivity::class.java)
                startActivity(intent)


                return@setOnNavigationItemSelectedListener true
            }


        }
    }

    private fun signout(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                // ...
                Toast.makeText(this, "You are signed Out", Toast.LENGTH_SHORT).show()
            }
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }

    fun getProfileInfo(): Participant {
        val service = ServiceBuilder.buildService(BackEndService::class.java)
        val requestCall = service.getParticipant(id)
        var participant = requestCall.execute().body()!!
        var profile = Participant(
            participant.id,
            participant.name,
            participant.email,
            participant.age,
            participant.gender,
            participant.occupation,
            participant.institution,
            participant.phone,
            participant.nationality,
            participant.photo
        )
        return profile


    }


}

