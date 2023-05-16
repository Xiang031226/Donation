package com.example.donation

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Patterns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentLoginBinding
import java.security.MessageDigest

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    companion object {
        private var userType = "user"
    }

    private lateinit var binding: FragmentLoginBinding

    private fun navigateToCorrectScreen() {
        val userType = viewModel.userType.value
        val intent = if (userType == "admin") {
            Intent(requireContext(), AdminActivity::class.java)
        } else {
            Intent(requireContext(), UserActivity::class.java)
        }
        startActivity(intent)
        requireActivity().finish()      //end the mainActivity, so user pressed back button can directly exit the apps
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                navigateToCorrectScreen()
//                viewModel.logout()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentLoginBinding.bind(view)
        binding.userTab.performClick()
        binding.apply {

            userTab.setOnClickListener {
                registerNowButton.isClickable = true
                registerNowButton.visibility = View.VISIBLE
                userType = "user"
                Toast.makeText(activity, "haiya", Toast.LENGTH_SHORT).show()
                changeTab(userType)
            }

            adminTab.setOnClickListener {
                registerNowButton.isClickable = false
                registerNowButton.visibility = View.INVISIBLE
                userType = "admin"
                changeTab(userType)
            }

            loginButton.setOnClickListener {
                loginValidation()
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
//        val user = userDao.getUserById(userId) ?: return false

        if (emailInput.isBlank()) {
            binding.loginEmailInputText.error = "Please enter your username or email"
            return
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                binding.loginEmailInputText.error = "Please enter a valid email"
                return
            }
        }
        if (passwordInput.isBlank()) {
            binding.loginPasswordInputText.error = "Please enter your password"
            return
        } else {
//            val storedPassword = user.password
//            val enteredPasswordHash = hashPassword(passwordInput)
//            if (enteredPasswordHash == storedPassword) {
//                loadMainActivity()
//            } else {
//                return
//            }
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
        viewModel.setUserType(userType)
        Log.e("??: ", userType)
        viewModel.apply {
            setLoggedIn(true)
            saveLoginState()

//        if(userType == "admin"){
//            val adminIntent = Intent(activity, AdminActivity::class.java)
//            startActivity(adminIntent)
//        }else {
//            val userIntent = Intent(activity, UserMainActivity::class.java)
//            startActivity(userIntent)
//        }
        }
    }

    private fun changeTab(userType: String) {
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
        Navigation.findNavController(it).navigate(R.id.register_fragment)
    }
}

