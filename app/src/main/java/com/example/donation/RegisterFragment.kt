package com.example.donation

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.donation.dao.UserDao
import com.example.donation.data.User
import com.example.donation.database.AppDatabase
import com.example.donation.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.regex.Pattern

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userDao: UserDao
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%/])(?=\\S+$).{4,}$"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.apply {
            registerButton.setOnClickListener {
                if (registerValidation()) {
                    addToDb()
                }
            }
        }
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

    private fun addToDb() {
        userDao = AppDatabase.getInstance(requireContext()).userDao()

        val emailInput = binding.registerEmailInputText.text.toString().trim()
        val nameInput = binding.registerNameInputText.text.toString().trim()
        val usernameInput = binding.registerUsernameInputText.text.toString().trim()
        val retypePasswordInput = binding.registerRetypePasswordInputText.text.toString().trim()

        val user = User(
            name = nameInput,
            username = usernameInput,
            email = emailInput,
            password = hashPassword(retypePasswordInput)
        )

        lifecycleScope.launch(Dispatchers.IO) {
            userDao.insert(user)

        }
        backToLoginFragment()
    }

    private fun backToLoginFragment() {
        Toast.makeText(activity, "You are registered. Login again", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
    }
}