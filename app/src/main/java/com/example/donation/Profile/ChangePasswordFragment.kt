package com.example.donation.Profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.LoginViewModel
import com.example.donation.MainActivity
import com.example.donation.R
import com.example.donation.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChangePasswordBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        binding.apply {
            changePasswordToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
            changePasswordToolbar.setNavigationOnClickListener { findNavController().popBackStack() }

            updatePasswordButton.setOnClickListener {
                viewModel.logout()
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

    }

}