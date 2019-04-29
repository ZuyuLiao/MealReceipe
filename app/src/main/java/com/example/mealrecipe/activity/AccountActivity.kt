package com.example.mealrecipe.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mealrecipe.R
import com.example.mealrecipe.adapter.AccountPagerAdapter
import kotlinx.android.synthetic.main.activity_account.*



class AccountActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val adapter = AccountPagerAdapter(this, supportFragmentManager)

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }
}
