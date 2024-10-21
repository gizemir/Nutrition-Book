package com.gizemir.nutritionbook.service

import com.gizemir.nutritionbook.model.Nutrition
import retrofit2.http.GET

interface NutritionAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json")
    //yazılan url'ye istek attık veriler için(retrofit ile)(get methodu retroift sayesinde geldi)
    suspend fun getNutrition(): List<Nutrition>
    //coroutine fonksiyonuyla besinleri listeledik
}
