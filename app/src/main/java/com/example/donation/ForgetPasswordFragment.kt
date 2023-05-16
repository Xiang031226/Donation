package com.example.donation

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment(R.layout.fragment_forget_password) {

    private lateinit var binding : FragmentForgetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgetPasswordBinding.bind(view)

        binding.resetPwButton.setOnClickListener {

            val email = binding.resetPwEmailText
            val emailText = email.text.toString().trim()

            if (emailText.isEmpty()) {
                email.error = "Email is required"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                email.error = "Please enter a valid email"
            } else {
                // Proceed with password reset logic
            }

        }
    }
}