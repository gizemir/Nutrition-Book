package com.gizemir.nutritionbook.service

import com.gizemir.nutritionbook.model.Nutrition
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutritionAPIService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        //bu fonksiyon, retrofit'e bizim atacağımız istek sonucunun Json tipinde geleceğini bildiriyor
        .build()
        .create(NutritionAPI::class.java)

    suspend fun getData(): List<Nutrition>{
        return retrofit.getNutrition()
    }
}