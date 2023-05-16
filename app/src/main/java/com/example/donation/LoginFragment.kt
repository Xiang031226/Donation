package com.example.donation

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentLoginBinding
import java.security.MessageDigest

class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object {
        private var userType = "user"
    }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        binding.apply {

            userTab.setOnClickListener {
                registerNowButton.isClickable = true
                registerNowButton.visibility = View.VISIBLE
                userType = "user"
                changeTab(userType)
            }

            adminTab.setOnClickListener {
                registerNowButton.isClickable = false
                registerNowButton.visibility = View.INVISIBLE
                userType = "admin"
                changeTab(userType)
            }

            loginButton.setOnClickListener {
                loadMainActivity() //temporary
//                loginValidation()
            }

            forgetPwButton.setOnClickListener {
                findNavController().navigate(R.id.forget_password_fragment)
            }

            registerNowButton.setOnClickListener {
                startRegisterFragment(it)
            }
        }
    }

    private fun loginValidation() {
        val emailInput = binding.loginEmailInputText.text.toString().trim()
        val passwordInput = binding.loginPasswordInputText.text.toString().trim()

        if (emailInput.isBlank()) {
            binding.loginEmailInputText.error = "Please enter your username or email"
            return
        }
        if (passwordInput.isBlank()) {
            binding.loginPasswordInputText.error = "Please enter your password"
            return
        } else {
//            val user = userDao.getUserById(userId) ?: return false
//            val storedPassword = user.password
//            val enteredPasswordHash = hashPassword(enteredPassword)
//            return enteredPasswordHash == storedPassword
        }
        loadMainActivity()
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun loadMainActivity() {

        if(userType == "admin"){
            val adminIntent = Intent(activity, AdminActivity::class.java)
            startActivity(adminIntent)
        }else {
            val userIntent = Intent(activity, UserMainActivity::class.java)
            startActivity(userIntent)
        }
    }

    private fun changeTab(userType : String) {
        lateinit var selected: TextView
        lateinit var nonSelected: TextView

        if (userType == "user") {
            selected = binding.userTab
            nonSelected = binding.adminTab
        } else {
            selected = binding.adminTab
            nonSelected = binding.userTab
        }

        //Changing background and text color of the tabs
        selected.setBackgroundResource(R.drawable.tab_active_shape)
        selected.setTypeface(null, Typeface.BOLD)

        selected.setTextColor(Color.parseColor("#FFFFFF"))
        nonSelected.setBackgroundResource(R.color.transparent)
        nonSelected.setTextColor(Color.parseColor("#4D000000"))
    }

    private fun startRegisterFragment(it: View) {
        Navigation.findNavController(it).navigate(R.id.action_login_fragment_to_register_fragment)
    }
}

