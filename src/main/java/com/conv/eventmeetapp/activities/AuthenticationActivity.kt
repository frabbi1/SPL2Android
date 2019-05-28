package com.conv.eventmeetapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.conv.eventmeetapp.models.Participant
import com.conv.eventmeetapp.R
import com.conv.eventmeetapp.models.CurrentUser
import com.conv.eventmeetapp.services.BackEndService
import com.conv.eventmeetapp.services.ServiceBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.authentication.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import retrofit2.Call
import retrofit2.Response


import java.io.Serializable


class AuthenticationActivity : AppCompatActivity(), Serializable {
    val RC_SIGN_IN = 9001
    private var count: Int = 0
    var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
       login.setOnClickListener {

           var signInIntent : Intent = mGoogleSignInClient!!.signInIntent
           startActivityForResult(signInIntent,RC_SIGN_IN)

       }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)


            updateUI(account)
        } catch (e: ApiException) {

            updateUI(null)
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account==null){
            Toast.makeText(this, "Log in failed", Toast.LENGTH_LONG).show()

        }

        else{
            account?.let {
                var name :String = account.displayName.toString()
                var email = account.email.toString()
                var id = account.id.toString()
                var photo = account.photoUrl.toString()

                CurrentUser.setUser(id)
                //Log.i("AuthenticationActivity", name)
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
                isNewUser(id,name,email,photo)


            }
        }


    }
    override fun onBackPressed() {
        count += 1
        if(count>=1){
            var i = Intent(Intent.ACTION_MAIN)
            i.addCategory(Intent.CATEGORY_HOME)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        else{

            Toast.makeText(this, "Press back again to Leave!", Toast.LENGTH_SHORT).show()

            // resetting the counter in 2s
            val handler = Handler()
            handler.postDelayed(Runnable { count = 0 }, 2000)
        }
        super.onBackPressed()


    }
    private fun isNewUser(id:String,name:String, email:String, photo:String){
        val service = ServiceBuilder.buildService(BackEndService::class.java)
        val requestCall = service.checkNewUser(id)
        var value:String

        requestCall.enqueue(object : retrofit2.Callback<Participant>{
            override fun onResponse(call: Call<Participant>, response: Response<Participant>){
                if(response.isSuccessful){
                    var temp = response.body()
                    value = temp?.id.toString()

                    if(value == "0"){
                        val intent = Intent(this@AuthenticationActivity, InitialEditProfile::class.java)
                        intent.putExtra("name",name)
                        intent.putExtra("email",email)
                        intent.putExtra("id",id)
                        intent.putExtra("photo",photo)
                        startActivity(intent)
                    }else{
                        val intent = Intent(this@AuthenticationActivity, Navigation::class.java)
                        intent.putExtra("id",temp?.id.toString())
                        startActivity(intent)

                    }


                }else{
                    Toast.makeText(this@AuthenticationActivity, "Failed to retrieve details", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Participant>, t: Throwable) {
                finish()
                Toast.makeText(this@AuthenticationActivity, "Failed to retrieve details :(", Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }





}

