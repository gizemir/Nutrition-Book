package com.gizemir.nutritionbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Nutrition(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val nutritionName: String?,

    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val nutritionCalori: String?,

    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val nutritionCarbohydrate: String?,

    @ColumnInfo("protein")
    @SerializedName("protein")
    val nutritionProtein: String?,

    @ColumnInfo("yag")
    @SerializedName("yag")
    val nutritionFat: String?,

    @ColumnInfo("gorsel")
    @SerializedName("gorsel")
    val nutritionImage: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
