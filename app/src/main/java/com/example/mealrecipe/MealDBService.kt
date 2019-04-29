package com.example.mealrecipe

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MealDBService{
    @GET("search.php?s=")
    fun searchMeal(@Query("s")meal:String) :Call<MealList>

    @GET("latest.php")
    fun latestMeal():Call<MealList>
}