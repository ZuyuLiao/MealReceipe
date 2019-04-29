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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mealrecipe.Meal
import com.example.mealrecipe.MealDBService
import com.example.mealrecipe.MealList
import com.example.mealrecipe.R
import com.example.mealrecipe.activity.MealDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import kotlinx.android.synthetic.main.search_result_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@SuppressLint("ValidFragment")
class SearchResultFragment(context: Context, query:String):Fragment(){
    var mealList:ArrayList<Meal> = ArrayList()
    val parentContext = context
    var baseURL="https://www.themealdb.com/api/json/v1/1/"
    var httpClient = okhttp3.OkHttpClient.Builder()
    var adapter = SearchResultMealAdapter()
    var query = query



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_result,container,false)
    }

    override fun onStart() {
        super.onStart()
        val builder = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.baseUrl(baseURL).client(httpClient.build()).build()
        val meal = retrofit.create(MealDBService::class.java)
        var call = meal.searchMeal(query)

        result_list.layoutManager = LinearLayoutManager(parentContext)
        result_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        result_list.adapter = adapter

        call.enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.isSuccessful) {
                    if(response.body()!!.meals != null) {
                        mealList = response.body()!!.meals
                        activity?.runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    }
                    else
                        Toast.makeText(parentContext,"Nothing Found~~",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Toast.makeText(parentContext,"No Result Found",Toast.LENGTH_SHORT).show()
            }
        })

//        FetchMeal().execute()
//
//        mealList.observe(this, android.arch.lifecycle.Observer<ArrayList<Meal>> {
//            it?.let {
//                adapter.notifyDataSetChanged()}
//        })
    }

    inner class SearchResultMealAdapter(): RecyclerView.Adapter<SearchResultMealAdapter.MealViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchResultMealAdapter.MealViewHolder {
            val itemView = LayoutInflater.from(p0.context).inflate(R.layout.search_result_layout, p0, false)
            return MealViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return mealList.size
        }

        override fun onBindViewHolder(p0: SearchResultMealAdapter.MealViewHolder, p1: Int) {
            val meal = mealList[p1]
            val productImages = meal.strMealThumb
            Picasso.with(parentContext).load(productImages).into(p0.productImg)

            p0.productTitle.text = meal.strMeal

            p0.row.setOnClickListener {
                val intent = Intent(parentContext, MealDetailActivity::class.java)
                intent.putExtra("meal", meal)
                startActivity(intent)
            }
        }
        inner class MealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val row = itemView

            var productImg: ImageView = itemView.mealImage1
            var productTitle: TextView = itemView.mealName1
        }
    }
}