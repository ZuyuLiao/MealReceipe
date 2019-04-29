package com.example.mealrecipe.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.mealrecipe.App
import com.example.mealrecipe.R
import com.example.mealrecipe.adapter.UserPagerAdapter
import com.example.mealrecipe.util.PostUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(App.firebaseAuth?.currentUser?.uid.toString()).get().addOnCompleteListener {
            PostUtils.currentUserName = it.result!!["username"] as String
        }
        val adapter = UserPagerAdapter(this, supportFragmentManager)

        viewpager1.adapter = adapter
        //viewpager1.offscreenPageLimit = 500
        tabUser.setupWithViewPager(viewpager1)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.logout_item -> {
                App.firebaseAuth?.signOut()
                Log.e("User", "Logout")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}