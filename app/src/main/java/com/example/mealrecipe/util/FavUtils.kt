package com.example.mealrecipe.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.mealrecipe.App
import com.example.mealrecipe.Meal
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class FavUtils {

    companion object {
        val db = FirebaseFirestore.getInstance()
        val userId = App.firebaseAuth?.currentUser?.uid

        fun getUserFavList() {
            var collection = db.document("favorites/$userId").collection("favMeals")
            collection.get()
                .addOnSuccessListener { documents ->
                    for(document in documents) {

                    }
                }
        }

        fun addFav(parentContext:Context, meal: Meal ) {
            var idMeal = meal.idMeal
            var document = db.document("favorites/$userId").collection("favMeals").document("$idMeal")
            val recordData = HashMap<String, Any>()
            recordData["idMeal"] = meal.idMeal
            recordData["strMeal"] = meal.strMeal
            recordData["strCategory"] = meal.strCategory
            recordData["strArea"] = meal.strArea
            recordData["strInstructions"] = meal.strInstructions
            recordData["strMealThumb"] = meal.strMealThumb
            recordData["strTags"] = meal.strTags
            recordData["strYoutube"] = meal.strYoutube
            recordData["strIngredient1"] = meal.strIngredient1
            recordData["strIngredient2"] = meal.strIngredient2
            recordData["strIngredient3"] = meal.strIngredient3
            recordData["strIngredient4"] = meal.strIngredient4
            recordData["strIngredient5"] = meal.strIngredient5
            recordData["strIngredient6"] = meal.strIngredient6
            recordData["strIngredient7"] = meal.strIngredient7
            recordData["strIngredient8"] = meal.strIngredient8
            recordData["strIngredient9"] = meal.strIngredient9
            recordData["strIngredient10"] = meal.strIngredient10
            recordData["strIngredient11"] = meal.strIngredient11
            recordData["strIngredient12"] = meal.strIngredient12
            recordData["strIngredient13"] = meal.strIngredient13
            recordData["strIngredient14"] = meal.strIngredient14
            recordData["strIngredient15"] = meal.strIngredient15
            recordData["strIngredient16"] = meal.strIngredient16
            recordData["strIngredient17"] = meal.strIngredient17
            recordData["strIngredient18"] = meal.strIngredient18
            recordData["strIngredient19"] = meal.strIngredient19
            recordData["strIngredient20"] = meal.strIngredient20
            recordData["strMeasure1"] = meal.strMeasure1
            recordData["strMeasure2"] = meal.strMeasure2
            recordData["strMeasure3"] = meal.strMeasure3
            recordData["strMeasure4"] = meal.strMeasure4
            recordData["strMeasure5"] = meal.strMeasure5
            recordData["strMeasure6"] = meal.strMeasure6
            recordData["strMeasure7"] = meal.strMeasure7
            recordData["strMeasure8"] = meal.strMeasure8
            recordData["strMeasure9"] = meal.strMeasure9
            recordData["strMeasure10"] = meal.strMeasure10
            recordData["strMeasure11"] = meal.strMeasure11
            recordData["strMeasure12"] = meal.strMeasure12
            recordData["strMeasure13"] = meal.strMeasure13
            recordData["strMeasure14"] = meal.strMeasure14
            recordData["strMeasure15"] = meal.strMeasure15
            recordData["strMeasure16"] = meal.strMeasure16
            recordData["strMeasure17"] = meal.strMeasure17
            recordData["strMeasure18"] = meal.strMeasure18
            recordData["strMeasure19"] = meal.strMeasure19
            recordData["strMeasure20"] = meal.strMeasure20


            document.set(recordData)
                .addOnSuccessListener {
                    Log.d("add Favorite Meal $idMeal", "success")
                    Toast.makeText(parentContext, "Add Fav success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Log.d("add Favorite Meal $idMeal", "fail")
                    Toast.makeText(parentContext, "Add Fav fail", Toast.LENGTH_SHORT).show()
                }
        }

        fun deleteFav(parentContext:Context, meal: Meal) {
            var idMeal = meal.idMeal
            var document = db.document("favorites/$userId").collection("favMeals").document("$idMeal")
            document.delete()
                .addOnSuccessListener {
                    Log.d("delete Favorite Meal $idMeal", "success")
                    Toast.makeText(parentContext, "Delete Fav success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.d("delete Favorite Meal $idMeal", "fail")
                    Toast.makeText(parentContext, "Delete Fav fail", Toast.LENGTH_SHORT).show()
                }
        }

        fun isInFavList(meal: Meal){
            var idMeal = meal.idMeal
            var document = db.document("favorites/$userId").collection("favMeals").document("$idMeal")

            document.get().addOnCompleteListener {
                if(it.result?.exists()!!){
                    meal.isFavorite = true
                }
            }
        }
    }
}