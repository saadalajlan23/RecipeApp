package com.example.recipeapp


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("recipes/")
    fun getAll(): Call<Recipe>

    @POST("recipes/")
    fun addUser(@Body userData: RecipeList): Call<RecipeList>
}