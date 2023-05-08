package com.example.donation

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.donation.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object {
        //TEST
        private var userType = "user"
    }
    private lateinit var loginFragmentBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(MainActivity.userType == "admin")
            loginFragmentBinding.registerNowButton.visibility = View.GONE

        loginFragmentBinding = FragmentLoginBinding.bind(view)
        loginFragmentBinding.apply {
            userTab.setOnClickListener {
                registerNowButton.isClickable = true
                registerNowButton.setTextColor(Color.parseColor("#000000"))
                userType = "user"
                changeTab(userType)
            }

            adminTab.setOnClickListener {
                registerNowButton.isClickable = false
                registerNowButton.setTextColor(Color.parseColor("#FFFFFF"))
                userType = "admin"
                changeTab(userType)
            }

            loginButton.setOnClickListener {
                loadMainActivity()
//                loginValidation()
            }

            registerNowButton.setOnClickListener {
                startRegisterFragment(it)
            }
        }
    }

    private fun loginValidation() {
        val emailInput = loginFragmentBinding.loginEmailInputText.text.toString()
        val passwordInput = loginFragmentBinding.loginPasswordInputText.text.toString()

        if (emailInput.isBlank()) {
            Toast.makeText(activity, "Please enter your username or email", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (passwordInput.isBlank()) {
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

    private fun changeTab(userType : String) {
        lateinit var selected: TextView
        lateinit var nonSelected: TextView

        if (userType == "user") {
            selected = loginFragmentBinding.userTab
            nonSelected = loginFragmentBinding.adminTab
        } else {
            selected = loginFragmentBinding.adminTab
            nonSelected = loginFragmentBinding.userTab
        }

        //changing background and text color of the tabs
        selected.setBackgroundResource(R.drawable.tab_active_shape)
        selected.setTypeface(null, Typeface.BOLD)

        selected.setTextColor(Color.parseColor("#FFFFFF"))
        nonSelected.setBackgroundResource(R.color.transparent)
        nonSelected.setTextColor(Color.parseColor("#4D000000"))
    }

    private fun startRegisterFragment(it: View) {
        Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
    }
}

