package com.example.donation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.donation.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        binding.triggerLogin.setOnClickListener {
            startLogin()
        }
    }

    private fun startLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(loginIntent)
=======
        val bottomNavigationView = binding.bottomNavigationBar
        val navController = findNavController(R.id.myNavHostFragment)
        bottomNavigationView.setupWithNavController(navController)

//        NavigationBarView.OnItemSelectedListener {
//            item -> when(item.itemId) {
//                R.id.homeFragment -> { true }
//                R.id.campaignFragment -> { true }
//                R.id.profileFragment -> { true }
//            else -> { false }
//        }
//        }
>>>>>>> c000072fd42d2b17197be9345ee3b8f8cbc89462
    }
}