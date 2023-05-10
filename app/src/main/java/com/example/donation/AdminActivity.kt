package com.example.donation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var activityAdminBinding: ActivityAdminBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminBinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(activityAdminBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_admin) as NavHost
        navController = navHostFragment.navController

        val bottomNavView = activityAdminBinding.adminBottomNavBar
        setupWithNavController(bottomNavView, navController)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        activityAdminBinding.settings.setOnClickListener{
            viewModel.logout()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() //finish the current activity, which is adminActivity
        }



    }
}