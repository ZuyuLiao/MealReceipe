package com.example.mealrecipe.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.mealrecipe.fragment.FavoriteFragment
import com.example.mealrecipe.fragment.ForumFragment
import com.example.mealrecipe.fragment.SearchFragment


class UserPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(p0: Int): Fragment {
        return if(p0 == 0) {
            SearchFragment(context)

        } else if (p0 == 1) {
            FavoriteFragment(context)
        }
        else{
            ForumFragment(context)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "Search"
        } else if (position == 1){
            "Favorite"
        }else {
            "Forum"
        }
    }
}