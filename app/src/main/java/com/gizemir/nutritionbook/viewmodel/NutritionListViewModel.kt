package com.gizemir.nutritionbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gizemir.nutritionbook.model.Nutrition
import com.gizemir.nutritionbook.roomdb.NutritionDatabase
import com.gizemir.nutritionbook.service.NutritionAPIService
import com.gizemir.nutritionbook.util.MySharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NutritionListViewModel(application: Application): AndroidViewModel(application){
    //normal "ViewModel" yerine "AndroidViewModel" kullanmamızın sebebi contexte erişebilmek

    val nutritions = MutableLiveData<List<Nutrition>>()
    val nutritionsErrorMessage = MutableLiveData<Boolean>()
    val nutritionsUploading = MutableLiveData<Boolean>()

    private val nutritionApiService = NutritionAPIService()
    private val mySharedPreferences = MySharedPreferences(getApplication())

    private val updateTime = 10*60*1000*1000*1000L
    fun refreshData(){
        val savingTime = mySharedPreferences.getTime()
        if(savingTime != null && savingTime != 0L && System.nanoTime() - savingTime < updateTime){
            //roomdan verileri alacağız
            getDataFromRoom()
        }else{
            getDataFromInternet()
        }
    }
    fun refreshDataFromInternet(){
        getDataFromInternet()
    }
    fun getDataFromRoom(){
        nutritionsUploading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutritionList = NutritionDatabase(getApplication()).nutritionDao().getAllNutrition()
            withContext(Dispatchers.Main){
                showNutrition(nutritionList)
                Toast.makeText(getApplication(), "get data from room", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getDataFromInternet(){
        nutritionsUploading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutritionList = nutritionApiService.getData()
            withContext(Dispatchers.Main){
                nutritionsUploading.value = false
                //room'a kaydedeceğiz
                saveRoom(nutritionList)
                Toast.makeText(getApplication(), "get data from internet", Toast.LENGTH_LONG).show()
            }
        }
    }

    private  fun showNutrition(nutritionList: List<Nutrition>){
        nutritions.value = nutritionList
        nutritionsErrorMessage.value = false
        nutritionsUploading.value = false

    }

    private fun saveRoom(nutritionList: List<Nutrition>){
        viewModelScope.launch {
            val dao = NutritionDatabase(getApplication()).nutritionDao()
            dao.deleteAllNutrition()
            val uuidList = dao.insertAll(*nutritionList.toTypedArray())
            var i = 0
            while (i < nutritionList.size){
                nutritionList[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            showNutrition(nutritionList)
        }
        mySharedPreferences.saveTime(System.nanoTime())
    }


}