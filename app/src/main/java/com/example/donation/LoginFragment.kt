package com.example.donation

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.donation.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    companion object {
        private var userType = "user"
    }

    private lateinit var loginFragmentBinding: FragmentLoginBinding

    private fun navigateToCorrectScreen() {
        val userType = viewModel.userType.value
        val intent = if(userType == "admin") {
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


        loginFragmentBinding = FragmentLoginBinding.bind(view)
        loginFragmentBinding.userTab.performClick()
        loginFragmentBinding.apply {

            userTab.setOnClickListener {
                registerNowButton.isClickable = true
                registerNowButton.setTextColor(Color.parseColor("#000000"))
                userType = "user"
                Toast.makeText(activity, "haiya", Toast.LENGTH_SHORT).show()
                changeTab(userType)
            }

            adminTab.setOnClickListener {
                registerNowButton.isClickable = false
                registerNowButton.setTextColor(Color.parseColor("#FFFFFF"))
                userType = "admin"
                changeTab(userType)
            }

            loginButton.setOnClickListener {
                loadMainActivity() //temporary
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
//            loadMainActivity()
        }
    }

    private fun loadMainActivity() {
        viewModel.setUserType(userType)
        Log.e("??: ", userType)
        viewModel.apply {
            setLoggedIn(true)
            saveLoginState()
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
        Navigation.findNavController(it).navigate(R.id.register_fragment)
    }
}

