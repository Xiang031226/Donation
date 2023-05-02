package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.donation.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var activityAdminBinding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminBinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(activityAdminBinding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, DashboardFragment())
            addToBackStack(null)
            commit()
        }
    }
}