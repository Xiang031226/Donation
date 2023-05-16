package com.example.donation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.donation.databinding.ActivityUserBinding
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel
    private lateinit var prefs: SharedPreferences

    @SuppressLint("CommitPrefEdits", "QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("Theme_Mode", Context.MODE_PRIVATE)
        val savedThemeMode =
            prefs.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        val isDarkModeOn = savedThemeMode == AppCompatDelegate.MODE_NIGHT_YES
        binding.switchToggle.isChecked = isDarkModeOn
        updateThemeTextView(isDarkModeOn)

        binding.switchToggle.setOnCheckedChangeListener { _, isChecked ->
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            updateTheme(isChecked)
        }

        val bottomNavigationView = binding.bottomNavigationBar
        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)


        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.campaign, R.id.profile)
        )
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)



        binding.apply {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            binding.topBarMenu.setOnClickListener{ drawerLayout.openDrawer(GravityCompat.START) }

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.notification -> {
                        Toast.makeText(this@UserActivity, "first item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.contact_us -> {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:support@wildcare.com") // Replace with your email address
                            putExtra(Intent.EXTRA_SUBJECT, "Contact Us") // Replace with your email subject
                        }

                        if (emailIntent.resolveActivity(this@UserActivity.packageManager) != null) {
                            startActivity(emailIntent)
                        } else {
                            Toast.makeText(this@UserActivity, "No email app found.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    R.id.about_us -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.aboutUsFragment)
                    }
                    R.id.setting -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.settingFragment)
                    }
                    R.id.nav_logout -> {
                        viewModel = ViewModelProvider(this@UserActivity)[LoginViewModel::class.java]
                        viewModel.logout()
                        val intent = Intent(this@UserActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                false
            }
        }
    }

    private fun updateThemeTextView(isDarkModeOn: Boolean) {
        if (isDarkModeOn) {
            binding.currentTheme.text = "Night"
        } else {
            binding.currentTheme.text = "Day"
        }
    }

    private fun updateTheme(isDarkModeOn: Boolean) {
        val newThemeMode =
            if (isDarkModeOn) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        prefs.edit().putInt("MODE", newThemeMode).apply()
        AppCompatDelegate.setDefaultNightMode(newThemeMode)
        recreate()
    }
}