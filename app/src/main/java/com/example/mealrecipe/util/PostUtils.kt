package com.example.mealrecipe.util

import com.google.firebase.storage.FirebaseStorage

class PostUtils{
    companion object {
        val storage = FirebaseStorage.getInstance()
        var currentUserName =""
    }
}