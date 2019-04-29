package com.example.mealrecipe.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealrecipe.Post
import com.example.mealrecipe.R
import com.example.mealrecipe.activity.PostActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_forum.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import kotlinx.android.synthetic.main.post_layout.view.*
import java.util.*


@SuppressLint("ValidFragment")
class ForumFragment(context: Context):Fragment(){
    private val parentContext = context
    private var postList:LinkedList<Post> = LinkedList()
    private val adapter = PostAdapter()
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
            rootView = inflater.inflate(R.layout.activity_forum,container,false)
        }
        return rootView
    }


    override fun onStart() {
        super.onStart()
        postList.clear()
        post_button.setOnClickListener {
            startActivity(Intent(parentContext, PostActivity::class.java))
        }
        post_list.layoutManager = LinearLayoutManager(parentContext)
        post_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        post_list.adapter = adapter
        val db = FirebaseFirestore.getInstance()
        val ref = db.collection("posts").orderBy("timestamp", Query.Direction.DESCENDING).get()
        if(ref != null) {
            ref.addOnCompleteListener {

                    for (document in it.result!!) {
                        var name = document["name"] as String
                        var content = document["post"] as String
                        var pic = document["pic"] as String
                        postList.addLast(Post(name, content,pic))
                    }


                    activity?.runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }

            }
        }

    }

//    override fun onResume() {
//        super.onResume()
//        postList.clear()
//        post_list.adapter?.notifyDataSetChanged()
//        val db = FirebaseFirestore.getInstance()
//        val ref = db.collection("posts").get()
//        if(ref != null) {
//            ref.addOnCompleteListener {
//
//                for (document in it.result!!) {
//                    var name = document["name"] as String
//                    var content = document["post"] as String
//                    var pic = document["pic"] as String
//                    postList.add(Post(name, content,pic))
//                }
//
//                activity?.runOnUiThread {
//                    adapter.notifyDataSetChanged()
//                }
//
//            }
//        }
//    }

    inner class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
            val postView = LayoutInflater.from(p0.context).inflate(R.layout.post_layout, p0, false)
            return PostViewHolder(postView)
        }

        override fun getItemCount(): Int {
           return postList.size
        }

        override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
            val post = postList[p1]
            val postIMG = post.pic
            Picasso.with(parentContext).load(postIMG).into(p0.img)
            val postContent = post.content
            val postName = post.name+":"
            p0.content.text = postContent
            p0.name.text = postName
        }

        inner class PostViewHolder(view:View):RecyclerView.ViewHolder(view){
            val row = view
            var img = view.postPic
            var name = view.postName
            var content = view.postContent
        }
    }

}