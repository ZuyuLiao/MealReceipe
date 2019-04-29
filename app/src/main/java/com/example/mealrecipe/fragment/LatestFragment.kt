package com.example.mealrecipe.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.mealrecipe.*
import com.example.mealrecipe.activity.MealDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.android.synthetic.main.meal_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


@SuppressLint("ValidFragment")
class LatestFragment(context: Context):Fragment(){
    var mealList:ArrayList<Meal> = ArrayList()
    val parentContext = context
    var baseURL="https://www.themealdb.com/api/json/v1/1/"
    var httpClient = okhttp3.OkHttpClient.Builder()
    var adapter = LatestMealAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_latest, container, false)
    }

    override fun onStart() {
        super.onStart()
        val builder = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.baseUrl(baseURL).client(httpClient.build()).build()
        val meal = retrofit.create(MealDBService::class.java)
        var call = meal.latestMeal()

        latest_list.layoutManager = LinearLayoutManager(parentContext)
        latest_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        latest_list.adapter = adapter

        call.enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.isSuccessful) {
                    mealList   = response.body()!!.meals

                    activity?.runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                return
            }
        })


    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    inner class LatestMealAdapter(): RecyclerView.Adapter<LatestMealAdapter.MealViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LatestMealAdapter.MealViewHolder {
            val itemView = LayoutInflater.from(p0.context).inflate(R.layout.meal_layout, p0, false)
            return MealViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return mealList.size
        }

        override fun onBindViewHolder(p0: LatestMealAdapter.MealViewHolder, p1: Int) {
            val meal = mealList[p1]
            val productImages = meal.strMealThumb
            Picasso.with(parentContext).load(productImages).into(p0.productImg)

            p0.productTitle.text = meal.strMeal

            p0.row.setOnClickListener {
                val intent = Intent(parentContext,MealDetailActivity::class.java)
                intent.putExtra("meal",meal)
                startActivity(intent)
            }
        }
        inner class MealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val row = itemView

            var productImg: ImageView = itemView.mealImage
            var productTitle: TextView = itemView.mealName
        }
    }

//    inner  class FetchMeal: AsyncTask<Void, Void, ArrayList<Meal>>()
//    {
//        override fun doInBackground(vararg params: Void):ArrayList<Meal> {
//            val builder = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
//            val retrofit = builder.baseUrl(baseURL).client(httpClient.build()).build()
//            val meal = retrofit.create(MealDBService::class.java)
//            var call = meal.latestMeal()
//
//            return call.execute().body()!!.meals
//        }
//
//        override fun onPostExecute(result: ArrayList<Meal>) {
//            super.onPostExecute(result)
//            mealList.value  = result
//            adapter = LatestMealAdapter()
//        }
//
//    }



}