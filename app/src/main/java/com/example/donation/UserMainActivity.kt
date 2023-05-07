package com.example.donation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.donation.UserMainActivity.Companion.KEY_THEME_MODE
import com.example.donation.databinding.ActivityMainBinding
import com.example.donation.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding
    private lateinit var navController: NavController

    companion object {
        private const val KEY_THEME_MODE = "theme_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)

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

//        currentFragment = Home()
//        replaceFragment(currentFragment)

        binding.apply {
            topBarMenu.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.notification -> {
                        Toast.makeText(this@UserMainActivity, "first item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.contact_us -> {
                        Toast.makeText(
                            this@UserMainActivity,
                            "second item selected",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    R.id.about_us -> {
                        Toast.makeText(this@UserMainActivity, "third item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.setting -> {
                        Toast.makeText(
                            this@UserMainActivity,
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
                    Toast.makeText(this@UserMainActivity, "$e", Toast.LENGTH_SHORT).show()
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

//    private fun replaceFragment(fragment: Fragment) {
//        try {
//            currentFragment = fragment
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.frame_layout, fragment)
//                addToBackStack(null)
//                commit()
//            }
//        } catch (e:Exception) {
//            Log.d("Error Msg: ", "$e")
//        }
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        // Save the current fragment when the activity is paused or stopped
//        supportFragmentManager.putFragment(outState, "currentFragment", currentFragment)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        // Restore the current fragment when the activity is recreated
//        currentFragment =
//            supportFragmentManager.getFragment(savedInstanceState, "currentFragment") ?: Home()
//        replaceFragment(currentFragment)
//    }
}