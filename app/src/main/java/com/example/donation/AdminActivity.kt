package com.example.donation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_admin) as NavHost
        navController = navHostFragment.navController
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup the bottom nav bar with the nav controller
        val bottomNavView = binding.adminBottomNavBar
        setupWithNavController(bottomNavView, navController)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.settings.setOnClickListener{
            viewModel.logout()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
             //finish the current activity, which is adminActivity
        }

        prefs = getSharedPreferences("Theme_Mode", Context.MODE_PRIVATE)
        val isNightModeOn = prefs.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) == AppCompatDelegate.MODE_NIGHT_YES

        binding.changeThemeToggle.isChecked = isNightModeOn
        binding.changeThemeToggle.setOnCheckedChangeListener { _, isChecked ->
            val newThemeMode =
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            prefs.edit().putInt("MODE", newThemeMode).apply()
            AppCompatDelegate.setDefaultNightMode(newThemeMode)
        }
    }
}