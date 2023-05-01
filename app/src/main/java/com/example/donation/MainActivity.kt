package com.example.donation

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.donation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment

    companion object {
        private const val KEY_THEME_MODE = "theme_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val savedThemeMode =
            prefs.getInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // Set the theme mode according to the saved value
        AppCompatDelegate.setDefaultNightMode(savedThemeMode)
        setContentView(binding.root)
        // Create the fragment
        currentFragment = Home()
        replaceFragment(currentFragment)

        //no need to keep typing binding
        binding.apply {
            topBarMenu.setOnClickListener{drawerLayout.openDrawer(GravityCompat.START)}

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.notification -> {
                        Toast.makeText(this@MainActivity, "first item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.contact_us -> {
                        Toast.makeText(
                            this@MainActivity,
                            "second item selected",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    R.id.about_us -> {
                        Toast.makeText(this@MainActivity, "third item selected", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.setting -> {
                        Toast.makeText(
                            this@MainActivity,
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

    @SuppressLint("SetTextI18n")
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
        recreate()
    }

    private fun replaceFragment(fragment: Fragment) {
        currentFragment = fragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
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
            .replace(R.id.frame_layout, currentFragment)
            .commit()
    }
}
