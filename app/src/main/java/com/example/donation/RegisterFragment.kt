package com.example.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.donation.databinding.FragmentRegisterBinding
import java.util.regex.Pattern

class RegisterFragment : Fragment(R.layout.fragment_register) {

    companion object {
        const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%/])(?=\\S+$).{4,}$"
    }

    private lateinit var registerFragmentBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragmentBinding = FragmentRegisterBinding.bind(view)

        registerFragmentBinding.apply {
            registerButton.setOnClickListener {
                registerValidation()
            }
        }
    }

    private fun registerValidation() {
        val emailInput = registerFragmentBinding.registerEmailInputText.text.toString()
        val usernameInput = registerFragmentBinding.registerUsernameInputText.text.toString()
        val passwordInput = registerFragmentBinding.registerPasswordInputText.text.toString().trim()
        val retypePasswordInput =
            registerFragmentBinding.registerRetypePasswordInputText.text.toString().trim()

        //email
        if (emailInput.isBlank()) {
            Toast.makeText(activity, "Please enter your email", Toast.LENGTH_SHORT)
                .show()
            return
        }

        //username
        if (usernameInput.isBlank()) {
            Toast.makeText(activity, "Please enter your username", Toast.LENGTH_SHORT)
                .show()
            return
        }

        //password: length and pattern
        if (passwordInput.isBlank()) {
            Toast.makeText(activity, "Please enter your password", Toast.LENGTH_SHORT)
                .show()
            return
        } else if (passwordInput.length < 8) {
            Toast.makeText(activity, "Password should be more than 8 characters", Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            if (!passwordPattern(passwordInput)) {
                Toast.makeText(activity, "Please follow the pattern", Toast.LENGTH_SHORT)
                    .show()
                return
            }
        }

        //retype password
        if (retypePasswordInput != passwordInput) {
            Toast.makeText(activity, "Please retype the same password", Toast.LENGTH_SHORT)
                .show()
        } else {
            view?.let { backToLoginFragment(it) }
        }
    }

    private fun passwordPattern(password: String): Boolean {
        //At least one capital letter, one number, one symbol (@#$%/)
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)

        return matcher.matches();
    }

    private fun backToLoginFragment(it: View) {
        Toast.makeText(activity, "You are registered. Login again", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it).popBackStack()
    }
}