package com.example.donation

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.model.User
import com.example.donation.databinding.FragmentRegisterBinding
import java.security.MessageDigest
import java.util.regex.Pattern

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: AccountViewModel
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%/])(?=\\S+$).{4,}$"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        viewModel = ViewModelProvider(requireActivity())[AccountViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.apply {
            registerButton.setOnClickListener {
                if (registerValidation()) {
                    insertDataToDatabase()
                }
            }
        }
    }

    private fun insertDataToDatabase() {
        val emailInput = binding.registerEmailInputText.text.toString()
        val nameInput = binding.registerNameInputText.text.toString()
        val usernameInput = binding.registerUsernameInputText.text.toString()
        val passwordInput = binding.registerPasswordInputText.text.toString().trim()

        val user = User(name = nameInput, username = usernameInput, email = emailInput, password = hashPassword(passwordInput))
        viewModel.addUser(user)
        backToLoginFragment()
    }

    private fun registerValidation(): Boolean {
        val emailInput = binding.registerEmailInputText.text.toString()
        val nameInput = binding.registerNameInputText.text.toString()
        val usernameInput = binding.registerUsernameInputText.text.toString()
        val passwordInput = binding.registerPasswordInputText.text.toString().trim()
        val retypePasswordInput = binding.registerRetypePasswordInputText.text.toString().trim()

        //email
        if (emailInput.isBlank()) {
            binding.registerEmailInputText.error = "Please enter your email"
            return false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                binding.registerEmailInputText.error = "Please enter a valid email"
                return false
            }
        }

        //name
        if (nameInput.isBlank()) {
            binding.registerNameInputText.error = "Please enter your name"
            return false
        }

        //username
        if (usernameInput.isBlank()) {
            binding.registerUsernameInputText.error = "Please enter your username"
            return false
        }

        //password: length and pattern
        if (passwordInput.isBlank()) {
            binding.registerPasswordInputText.error = "Please enter your password"
            return false

        } else if (passwordInput.length < 8) {
            binding.registerPasswordInputText.error = "Password should be more than 8 characters"
            return false
        } else {
            if (!passwordPattern(passwordInput)) {
                binding.registerPasswordInputText.error = "Please follow the pattern"
                return false
            }
        }

        //retype password
        if (retypePasswordInput != passwordInput) {
            binding.registerRetypePasswordInputText.error = "Please retype the same password"
            return false
        }
        return true
    }

    private fun passwordPattern(password: String): Boolean {
        //At least one capital letter, one number, one symbol (@#$%/)
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)

        return matcher.matches();
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun backToLoginFragment() {
        Toast.makeText(activity, "You are registered. Login again", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
    }
}