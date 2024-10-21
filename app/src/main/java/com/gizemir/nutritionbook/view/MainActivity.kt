package com.gizemir.nutritionbook.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gizemir.nutritionbook.databinding.ActivityMainBinding
//10'dan az bi sürede uygulamaya giriliyorsa veriler room database'den alınacak
//10'dan fazla sürüyor ise veriler internetten alınacak

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}