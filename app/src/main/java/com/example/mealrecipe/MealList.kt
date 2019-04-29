package com.example.mealrecipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MealList(@SerializedName("meals")@Expose val meals:ArrayList<Meal>)