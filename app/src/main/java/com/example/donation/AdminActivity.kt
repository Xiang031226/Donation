package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_admin)
        navController = navHostFragment!!.findNavController()

        //Setup the bottom nav bar with the nav controller
        val bottomNavView = binding.adminBottomNavBar
        setupWithNavController(bottomNavView, navController)
    }
}