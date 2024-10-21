package com.gizemir.nutritionbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gizemir.nutritionbook.model.Nutrition
import com.gizemir.nutritionbook.roomdb.NutritionDatabase
import kotlinx.coroutines.launch

class NutritionDetailViewModel(application: Application): AndroidViewModel(application) {
    val nutritionLiveData = MutableLiveData<Nutrition>()
    fun getRoomData(uuid: Int){
        viewModelScope.launch {
            val dao = NutritionDatabase(getApplication()).nutritionDao()
            val nutrition = dao.getNutrition(uuid)
            nutritionLiveData.value = nutrition
        }
    }
}