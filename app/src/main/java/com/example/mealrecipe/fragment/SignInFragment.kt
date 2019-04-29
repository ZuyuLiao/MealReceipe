package com.example.cse438.blackjack.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mealrecipe.App
import com.example.mealrecipe.R
import com.example.mealrecipe.util.PostUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_sign_in.*

@SuppressLint("ValidFragment")
class SignInFragment(context: Context): Fragment() {
    private var parentContext = context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onStart() {
        super.onStart()

        sign_in_btn.setOnClickListener{
            val email = email_signin_pt.text.toString()
            val password = password_signin_pt.text.toString()

            App.firebaseAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener{ it2 ->
                if(it2.isSuccessful){
                    val db = FirebaseFirestore.getInstance()
                    db.document("users/${App.firebaseAuth?.currentUser?.uid}").get().addOnCompleteListener {
                        PostUtils.currentUserName = it.result!!["username"] as String
                        activity?.finish()
                    }
                } else {
                    Toast.makeText(parentContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}