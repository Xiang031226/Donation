package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.MediaController
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var activityAdminBinding: ActivityAdminBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminBinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(activityAdminBinding.root)

        //get login data from Login fragment
//        val adminBundle = intent.extras
//        activityAdminBinding.testview.text = adminBundle?.getString("username_email")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_admin) as NavHost
        navController = navHostFragment.navController

        val bottomNavView = activityAdminBinding.adminNavView
        setupWithNavController(bottomNavView, navController)

        activityAdminBinding.apply {
            adminBottomNavBar.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.dashboardFragment -> navController.navigate(R.id.dashboard_fragment)
                    R.id.campaignFragment -> navController.navigate(R.id.application_fragment)
//                    R.id.profileFragment ->
                }
                true
            }
        }
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.fcv_admin)
        navController.navigateUp()
        var bottomNavBar = activityAdminBinding.adminBottomNavBar
        when (navController.currentDestination?.id) {
            R.id.dashboard_fragment -> bottomNavBar.selectedItemId = R.id.dashboardFragment
            R.id.application_fragment -> bottomNavBar.selectedItemId = R.id.campaignFragment
            //profile fragment
        }
    }
}