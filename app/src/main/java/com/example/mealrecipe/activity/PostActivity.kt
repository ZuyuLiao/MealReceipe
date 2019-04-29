package com.example.mealrecipe.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mealrecipe.R
import kotlinx.android.synthetic.main.activity_post.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.Toast
import com.example.mealrecipe.util.FavUtils
import com.example.mealrecipe.util.PostUtils
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.util.*


class PostActivity:AppCompatActivity(){
    var REQUEST_IMAGE_CAPTURE: Int = 1
    val storageRef = PostUtils.storage.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mealrecipe.R.layout.activity_post)
        pictureSent.setOnClickListener {
            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE)
        }
        postButton.setOnClickListener {
            send()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK)
        {
            val imgBitmap =data?.extras?.get("data") as Bitmap
            pictureSent.setImageBitmap(imgBitmap)
        }
    }

    private fun send(){

            val picTag = getPicTag()
            val picRef = storageRef.child(picTag)
            pictureSent.isDrawingCacheEnabled = true
            pictureSent.buildDrawingCache()
            val bitmap = (pictureSent.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            var uploadTask = picRef.putBytes(data)
            val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation picRef.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result.toString()
                    val content = postContent.text.toString()
                    val db = FirebaseFirestore.getInstance()
                    val userData = HashMap<String, Any>()
                    userData["name"] = PostUtils.currentUserName
                    userData["post"] = content
                    userData["pic"] = downloadUri
                    userData["timestamp"] = FieldValue.serverTimestamp()
                    db.collection("posts").document(picTag).set(userData).addOnCompleteListener {
                        Toast.makeText(this,"Successfully Post",Toast.LENGTH_SHORT).show()
                        this.finish()
                    }

                } else {
                    // Handle failures
                    // ...
                }
            }


    }

    private fun getPicTag():String{
        val calendar = Calendar.getInstance()
        val time = calendar.get(Calendar.YEAR).toString() + (calendar.get(Calendar.MONTH)+1).toString()+ calendar.get(Calendar.DAY_OF_MONTH).toString()+ calendar.get(Calendar.HOUR_OF_DAY).toString()+
                calendar.get(Calendar.MINUTE).toString() + calendar.get(Calendar.SECOND).toString()
        return time

    }


}