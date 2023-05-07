package com.example.donation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.donation.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        var userType = ""
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, UserMainActivity::class.java)
//        startActivity(intent)

        val tabLayout = binding.tabLayout
        LoginFragment()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // handle tab selection event
                when (tab.position) {
                    0 -> {
                        userType = "user"
                        replaceFragment(LoginFragment())
                    }
                    1 -> {
                        userType = "admin"
                        replaceFragment(LoginFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_sign_in, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
