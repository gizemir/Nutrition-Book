package com.gizemir.nutritionbook.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gizemir.nutritionbook.model.Nutrition

@Dao
interface NutritionDAO {

    @Insert
    suspend fun insertAll(vararg nutrition: Nutrition): List<Long>
    //birden fazla besini aynı anda eklemek için
    //dezavantajı: eklediği besinlerin id'sini long olarak geri veriyor

    @Query("SELECT *FROM nutrition")
    suspend fun getAllNutrition(): List<Nutrition>
    //besin listesini getirir

    @Query("SELECT *FROM nutrition WHERE uuid = :nutritionId")
    suspend fun getNutrition(nutritionId: Int): Nutrition

    @Query("DELETE FROM nutrition")
    suspend fun deleteAllNutrition()
    //bütün besinleri silmek

}