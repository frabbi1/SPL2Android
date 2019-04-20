package com.conv.eventmeetapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.navigation.*


class Navigation : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private var name = ""
    private var email = ""
    private var id = ""
    private var photo = ""
    private var age = ""
    private var occupation = ""
    private var institution = ""
    private var nationality = ""
    private var gender = ""
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation)

        var intent = intent
        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")
        id = intent.getStringExtra("id")
        photo = intent.getStringExtra("photo")
        age = intent.getStringExtra("age")
        occupation = intent.getStringExtra("occupation")
        institution = intent.getStringExtra("institution")
        phone = intent.getStringExtra("phone")
        nationality = intent.getStringExtra("nationality")
        gender = intent.getStringExtra("gender")


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
                finish()


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
    fun getProfileData():Profile{
        var profiledData = Profile(id, name, email, age, gender, occupation, institution, phone, nationality, photo)
        return profiledData

    }


}
data class Profile(
    var id : String,
    var name : String,
    var email : String,
    var age : String,
    var gender : String,
    var occupation : String?,
    var institution : String?,
    var phone : String,
    var nationality : String?,
    var photo : String?
)
