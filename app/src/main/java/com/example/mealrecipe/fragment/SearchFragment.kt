package com.example.mealrecipe.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.mealrecipe.R
import kotlinx.android.synthetic.main.fragment_search.*


@SuppressLint("ValidFragment")
class SearchFragment(context: Context):Fragment(){

    private var parentContext = context
    private var initialized: Boolean = false
    private var rootView:View? = null

    override fun onDestroyView() {
        super.onDestroyView()
        if(rootView != null)
        {
            val viewGroup = rootView?.parent as ViewGroup
            viewGroup.removeView(rootView)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView==null)
        {
            rootView = inflater.inflate(R.layout.fragment_search,container,false)
        }
        return rootView
    }

    override fun onStart() {
        super.onStart()
        if(!initialized) {
            val fm = fragmentManager
            val ft = fm?.beginTransaction()
            ft?.add(R.id.list_hold, LatestFragment(parentContext), "load latest meal")
            ft?.commit()

            search_edit_text.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val searchText = search_edit_text.text
                    search_edit_text.setText("")
                    if (searchText.toString() == "") {
                        val toast = Toast.makeText(this.parentContext, "Please enter text", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                        return@setOnEditorActionListener true
                    }
                    else {
                        performSearch(searchText.toString())
                        return@setOnEditorActionListener false
                    }
                }

                return@setOnEditorActionListener false
            }

            this.initialized = true
        }

    }

    private fun performSearch(query: String) {
        // Load Fragment into View
        val fm = fragmentManager

        // add
        val fragment = SearchResultFragment(this.parentContext, query)
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.list_hold, fragment, "RESULTS_FRAG")?.addToBackStack("load latest meal")
        ft?.commit()
    }
}