package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.donation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //LZX
        //LV
        //v2
    }
}