package com.example.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
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

        login()
    }

    private fun login() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_login, LoginFragment())
            addToBackStack(null)
            commit()
        }
    }
}