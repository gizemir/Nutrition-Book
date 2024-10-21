package com.gizemir.nutritionbook.util

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences {

    companion object {

        private val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: MySharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: mySharedPreferencesMake(context).also {
                instance = it
            }
        }

        private fun mySharedPreferencesMake(context: Context): MySharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return  MySharedPreferences()
        }
    }
    fun saveTime(time: Long){
        sharedPreferences?.edit()?.putLong(TIME, time)?.apply()
    }

    fun getTime() = sharedPreferences?.getLong(TIME, 0)
}