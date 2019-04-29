package com.example.mealrecipe.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mealrecipe.App
import com.example.mealrecipe.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //User Sign up/ Sign In
        if (App.firebaseAuth == null) {
            App.firebaseAuth = FirebaseAuth.getInstance()
        }

        if (App.firebaseAuth != null && App.firebaseAuth?.currentUser == null) {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        if (App.firebaseAuth != null && App.firebaseAuth?.currentUser != null){
            Log.e("User", "Logged")
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}
