package com.example.donation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.donation.databinding.ActivityLoginBinding
import com.example.donation.databinding.LoginFragmentBinding
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var loginFragmentBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return loginFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(MainActivity.userType == "admin")
            loginFragmentBinding.registerNowButton.visibility = View.GONE

        loginFragmentBinding.apply {
            registerNowButton.setOnClickListener {
                startRegisterFragment()
            }

            loginButton.setOnClickListener {
                loginValidation()
            }
        }
    }

    private fun loginValidation() {
        val emailInput = loginFragmentBinding.loginEmailInputText.text.toString()
        val passwordInput = loginFragmentBinding.loginPasswordInputText.text.toString()

        if (emailInput == "") {
            Toast.makeText(activity, "Please enter your username or email", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (passwordInput == "") {
            Toast.makeText(activity, "Please enter your password", Toast.LENGTH_SHORT)
                .show()
        } else {
            loadMainActivity()
        }
    }

    private fun loadMainActivity(){
        if(MainActivity.userType == "user") {
            val userBundle = Bundle()
            val userIntent = Intent(activity, UserMainActivity()::class.java)
            userIntent.putExtras(userBundle)
            startActivity(userIntent)
        }else {
            val adminBundle = Bundle()
            val adminIntent = Intent(activity, AdminActivity()::class.java)
            adminIntent.putExtras(adminBundle)
            startActivity(adminIntent)
        }
    }

    private fun startRegisterFragment() {
        val registerFragment = RegisterFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_login, registerFragment)
            addToBackStack(null)
            commit()
        }
    }
}

