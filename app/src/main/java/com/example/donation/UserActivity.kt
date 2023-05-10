package com.example.donation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.donation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var navController: NavController

    companion object {
        private const val KEY_THEME_MODE = "theme_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val savedThemeMode =
            prefs.getInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        AppCompatDelegate.setDefaultNightMode(savedThemeMode)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationBar
        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.campaign,R.id.profile)
        )
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.apply {
            topBarMenu.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.notification -> {
                        Toast.makeText(this@UserActivity, "first item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.contact_us -> {
                        Toast.makeText(
                            this@UserActivity,
                            "second item selected",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    R.id.about_us -> {
                        Toast.makeText(this@UserActivity, "third item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.setting -> {
                        Toast.makeText(
                            this@UserActivity,
                            "fourth item selected",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                true
            }

            val isDarkModeOn = savedThemeMode == AppCompatDelegate.MODE_NIGHT_YES
            switchToggle.isChecked = isDarkModeOn
            updateThemeTextView(isDarkModeOn)

            switchToggle.setOnCheckedChangeListener { _, isChecked ->
                try {
                    Toast.makeText(applicationContext, "Lol", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    updateTheme(isChecked, prefs)
                } catch (e:Exception) {
                    Toast.makeText(this@UserActivity, "$e", Toast.LENGTH_SHORT).show()
                    Log.e("Msg: ", "$e")
                }
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

    private fun updateTheme(isDarkModeOn: Boolean, prefs: SharedPreferences) {
        if (isDarkModeOn) {
            prefs.edit().putInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_YES).apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            prefs.edit().putInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_NO).apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}