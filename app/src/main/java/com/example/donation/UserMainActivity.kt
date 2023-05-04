package com.example.donation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat.getExtras
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.donation.UserMainActivity.Companion.KEY_THEME_MODE
import com.example.donation.databinding.ActivityMainBinding
import com.example.donation.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding
    private lateinit var currentFragment: Fragment

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

        val navHeader = binding.navView.getHeaderView(0)
        val username = navHeader.findViewById<TextView>(R.id.username)
        username.text = intent.getStringExtra("username")

        currentFragment = Home()
        replaceFragment(currentFragment)

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
                Toast.makeText(applicationContext, "Lol", Toast.LENGTH_SHORT).show()
                drawerLayout.closeDrawer(GravityCompat.START)
                updateTheme(isChecked, prefs)
            }

            bottomNavigationBar.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> replaceFragment(Home())
                    R.id.campaignFragment -> replaceFragment(Campaign())
                    R.id.profileFragment -> replaceFragment(Profile())
                }
                true
            }
        }
    }

    private fun updateThemeTextView(isDarkModeOn: Boolean) {
        if (isDarkModeOn) {
            binding.currentTheme.text = "Day"
        } else {
            binding.currentTheme.text = "Night"
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

    private fun replaceFragment(fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the current fragment when the activity is paused or stopped
        supportFragmentManager.putFragment(outState, "currentFragment", currentFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore the current fragment when the activity is recreated
        currentFragment =
            supportFragmentManager.getFragment(savedInstanceState, "currentFragment") ?: Home()
        supportFragmentManager.beginTransaction()
    }
}