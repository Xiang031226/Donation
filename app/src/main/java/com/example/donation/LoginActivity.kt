package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.donation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
    }
}