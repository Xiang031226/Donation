package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toast
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var activityAdminBinding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminBinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(activityAdminBinding.root)

        //get login data from Login fragment
//        val adminBundle = intent.extras
//        activityAdminBinding.testview.text = adminBundle?.getString("username_email") //testinggg

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, DashboardFragment())
            addToBackStack(null)
            commit()
        }
    }
}