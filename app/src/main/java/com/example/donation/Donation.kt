package com.example.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.adapter.DonationCardAdapter
import com.example.donation.databinding.FragmentCampaignBinding
import com.example.donation.databinding.FragmentDonationBinding

class Donation : Fragment(R.layout.fragment_donation) {

    private lateinit var binding: FragmentDonationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDonationBinding.bind(view)

        binding.donationRecyclerView.adapter = DonationCardAdapter(
            context
        )

        binding.donationRecyclerView.setHasFixedSize(true)

    }
}