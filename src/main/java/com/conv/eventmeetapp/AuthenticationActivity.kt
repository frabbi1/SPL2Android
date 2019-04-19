package com.conv.eventmeetapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.authentication.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException

import java.io.Serializable


class AuthenticationActivity : AppCompatActivity(), Serializable {
    val RC_SIGN_IN = 9001
    private var count: Int = 0
    var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication)

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
       login.setOnClickListener {
           //Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show()
           var signInIntent : Intent = mGoogleSignInClient!!.signInIntent
           startActivityForResult(signInIntent,RC_SIGN_IN)

       }

    }



    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
           // Log.w(FragmentActivity.TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account==null){
            Toast.makeText(this, "Log in failed", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Navigation::class.java)

            startActivity(intent)
        }
        account?.let {
            var name :String = account.displayName.toString()
            var email = account.email.toString()
            var id = account.id.toString()
            var photo = account.photoUrl.toString()
            Log.i("AuthenticationActivity", name)
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Navigation::class.java)
            intent.putExtra("name",name)
            intent.putExtra("email",email)
            intent.putExtra("id",id)
            intent.putExtra("photo",photo)


            startActivity(intent)

        }


    }
    override fun onBackPressed() {
        count = count+1
        if(count>=1){
            var i = Intent(Intent.ACTION_MAIN)
            i.addCategory(Intent.CATEGORY_HOME)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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




}

