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
import com.example.mealrecipe.Meal
import com.example.mealrecipe.R
import com.example.mealrecipe.activity.MealDetailActivity
import com.example.mealrecipe.util.FavUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.meal_layout.view.*


@SuppressLint("ValidFragment")
class FavoriteFragment(context: Context):Fragment(){
    private var parentContext = context
    var mealList:ArrayList<Meal> = ArrayList()
    var adapter = FavoriteMealAdapter()
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
            rootView = inflater.inflate(R.layout.fragment_favorite_list,container,false)
        }
        return rootView
    }

    override fun onStart() {
        super.onStart()
        mealList.clear()
        var collection = FavUtils.db.document("favorites/${FavUtils.userId}").collection("favMeals")
        collection.get().addOnSuccessListener { documents ->
            for(document in documents) {
                var idMeal = document.get("idMeal").toString()
                var strMeal = document.get("strMeal").toString()
                var strCategory = document.get("strCategory").toString()
                var strArea = document.get("strArea").toString()
                var strInstructions = document.get("strInstructions").toString()
                var strMealThumb = document.get("strMealThumb").toString()
                var strTags = document.get("strTags").toString()
                var strYoutube = document.get("strYoutube").toString()
                var strIngredient1 = document.get("strIngredient1").toString()
                var strIngredient2 = document.get("strIngredient2").toString()
                var strIngredient3 = document.get("strIngredient3").toString()
                var strIngredient4 = document.get("strIngredient4").toString()
                var strIngredient5 = document.get("strIngredient5").toString()
                var strIngredient6 = document.get("strIngredient6").toString()
                var strIngredient7 = document.get("strIngredient7").toString()
                var strIngredient8 = document.get("strIngredient8").toString()
                var strIngredient9 = document.get("strIngredient9").toString()
                var strIngredient10 = document.get("strIngredient10").toString()
                var strIngredient11 = document.get("strIngredient11").toString()
                var strIngredient12 = document.get("strIngredient12").toString()
                var strIngredient13 = document.get("strIngredient13").toString()
                var strIngredient14 = document.get("strIngredient14").toString()
                var strIngredient15 = document.get("strIngredient15").toString()
                var strIngredient16 = document.get("strIngredient16").toString()
                var strIngredient17 = document.get("strIngredient17").toString()
                var strIngredient18 = document.get("strIngredient18").toString()
                var strIngredient19 = document.get("strIngredient19").toString()
                var strIngredient20 = document.get("strIngredient20").toString()
                var strMeasure1 = document.get("strMeasure1").toString()
                var strMeasure2 = document.get("strMeasure2").toString()
                var strMeasure3 = document.get("strMeasure3").toString()
                var strMeasure4 = document.get("strMeasure4").toString()
                var strMeasure5 = document.get("strMeasure5").toString()
                var strMeasure6 = document.get("strMeasure6").toString()
                var strMeasure7 = document.get("strMeasure7").toString()
                var strMeasure8 = document.get("strMeasure8").toString()
                var strMeasure9 = document.get("strMeasure9").toString()
                var strMeasure10 = document.get("strMeasure10").toString()
                var strMeasure11 = document.get("strMeasure11").toString()
                var strMeasure12 = document.get("strMeasure12").toString()
                var strMeasure13 = document.get("strMeasure13").toString()
                var strMeasure14 = document.get("strMeasure14").toString()
                var strMeasure15 = document.get("strMeasure15").toString()
                var strMeasure16 = document.get("strMeasure16").toString()
                var strMeasure17 = document.get("strMeasure17").toString()
                var strMeasure18 = document.get("strMeasure18").toString()
                var strMeasure19 = document.get("strMeasure19").toString()
                var strMeasure20 = document.get("strMeasure20").toString()

                var meal:Meal = Meal(idMeal,strMeal,strCategory,strArea,strInstructions,strMealThumb,strTags,strYoutube,
                        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,strIngredient6, strIngredient7,
                    strIngredient8,strIngredient9, strIngredient10, strIngredient11,  strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16,
                    strIngredient17, strIngredient18, strIngredient19, strIngredient20, strMeasure1, strMeasure2, strMeasure3, strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,strMeasure10,
                    strMeasure11, strMeasure12,strMeasure13,strMeasure14, strMeasure15, strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20,true)

                mealList.add(meal)
            }

            favorite_list.layoutManager = LinearLayoutManager(parentContext)
            favorite_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            favorite_list.adapter = adapter

        }

    }

    inner class FavoriteMealAdapter(): RecyclerView.Adapter<FavoriteMealAdapter.MealViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMealAdapter.MealViewHolder {
            val itemView = LayoutInflater.from(p0.context).inflate(R.layout.meal_layout, p0, false)
            return MealViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return mealList.size
        }

        override fun onBindViewHolder(p0: FavoriteMealAdapter.MealViewHolder, p1: Int) {
            val meal = mealList[p1]
            val productImages = meal.strMealThumb
            Picasso.with(parentContext).load(productImages).into(p0.productImg)

            p0.productTitle.text = meal.strMeal

            p0.row.setOnClickListener {
                val intent = Intent(parentContext, MealDetailActivity::class.java)
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



}