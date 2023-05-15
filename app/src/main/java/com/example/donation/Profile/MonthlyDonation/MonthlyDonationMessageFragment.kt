package com.example.donation.Profile.MonthlyDonation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.databinding.FragmentMonthlyDonationMessageBinding

class MonthlyDonationMessageFragment : Fragment() {

    private lateinit var binding : FragmentMonthlyDonationMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_donation_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMonthlyDonationMessageBinding.bind(view)


        binding.donateMonthlyButton.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_campaign)
        }
    }
}