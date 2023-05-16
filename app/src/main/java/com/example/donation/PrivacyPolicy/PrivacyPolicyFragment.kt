package com.example.donation.PrivacyPolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {

    private lateinit var binding : FragmentPrivacyPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPrivacyPolicyBinding.bind(view)

        binding.privacyPolicyToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.privacyPolicyToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}