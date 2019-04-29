package com.example.mealrecipe.activity

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Bundle
import android.support.design.internal.BaselineLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.mealrecipe.Meal
import com.example.mealrecipe.R
import com.example.mealrecipe.util.FavUtils
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.meal_detail_layout.*
import java.lang.reflect.Array

class MealDetailActivity:YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{
    private lateinit var meal: Meal
    private lateinit var id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_detail_layout)

        meal = intent.extras!!.getSerializable("meal") as Meal
        detailName.text = meal.strMeal
        category.text = meal.strCategory
        area.text = meal.strArea
        instruction.text = meal.strInstructions
        val instructions = meal.strInstructions.split("\\s+");
        val tag = meal.strTags
        val tags = tag.split(",")
        tag_group.setTags(tags)
        val index = meal.strYoutube.indexOf("=")
        id = meal.strYoutube.substring(index+1)

        // determine favourite







        if(meal.strIngredient1 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure1
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }
        if(meal.strIngredient2 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient2
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure2
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }
        if(meal.strIngredient3 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient3
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure3
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }
        if(meal.strIngredient4 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient4
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure4
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }
        if(meal.strIngredient5 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient5
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure5
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient6 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure6
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient7 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure7
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient8 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure8
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient9 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient9
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure9
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient10 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            //row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient10
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure10
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient11 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient11
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure11
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient12 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient12
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure12
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient13 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            var text = TextView(this)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure13
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient14 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient1
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure14
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient15 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient15
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure15
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient16 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient16
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure16
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient17 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient17
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure17
            text1.gravity = Gravity.CENTER
            text1.textSize = 18.0F
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient18 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient18
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure18
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient19 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient19
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure19
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }

        if(meal.strIngredient20 != "")
        {
            var row = TableRow(this)
            row.setPadding(20,10,20,20)
            row.setBackgroundResource(R.drawable.ingredient_row_shape)
            var text = TextView(this)
            text.text = meal.strIngredient20
            text.textSize = 18.0F
            row.addView(text)
            var text1 = TextView(this)
            text1.text = meal.strMeasure20
            text1.textSize = 18.0F
            text1.gravity = Gravity.CENTER
            row.addView(text1)
            ingredient.addView(row)
        }
        youtube.initialize("",this)

        fav_button.setOnClickListener{
            if(meal.isFavorite) {
                fav_button.setImageResource(R.mipmap.fav_blank)
                FavUtils.deleteFav(this,meal)
            } else{
                fav_button.setImageResource(R.mipmap.fav_filled)
                FavUtils.addFav(this, meal)
            }
            meal.isFavorite = !meal.isFavorite
        }

        back_button.setOnClickListener{
            this.finish()
        }

//        for(s in instructions) {
//            var row = TableRow(this)
//            row.setPadding(20,40,20,40)
//            var text = TextView(this)
//            text.text = s
//            text.textSize = 18.0F
//            text.setBackgroundResource(R.drawable.ingredient_row_shape)
//            row.addView(text)
//            preprations.addView(row)
//
//        }


    }

    override fun onStart() {
        super.onStart()

        var idMeal = meal.idMeal
        var document = FavUtils.db.document("favorites/${FavUtils.userId}").collection("favMeals").document("$idMeal")
        document.get().addOnCompleteListener {
            if(it.result?.exists()!!){
                meal.isFavorite = true
                fav_button.setImageResource(R.mipmap.fav_filled)
            }
        }
    }

    override fun onBackPressed() {
        this.finish()
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.cueVideo(id)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this,"video loading error",Toast.LENGTH_SHORT).show()
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.meal_detail_menu, menu)
//        if(this.meal.isFavorite) {
//            menu?.getItem(0)?.icon = getDrawable(R.drawable.ic_star_filled)
//        }
//        return  true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId) {
//            android.R.id.home -> {
//                this.finish()
//                return true
//            }
//            R.id.action_favorite -> {
//                if(meal.isFavorite) {
//                    item.icon = getDrawable(R.drawable.ic_star_blank)
////                    viewModel.removeFavorite(this.track.getName(), this.track.getArtist())
//
//                } else {
//                    item.icon = getDrawable(R.drawable.ic_star_filled)
////                    viewModel.addFavorite(this.track)
//                }
//                meal.isFavorite = !meal.isFavorite
//
//                return false
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
