package com.example.donation

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.donation.data.DonationSource.donation
import com.example.donation.databinding.FragmentCampaignBinding
import com.google.android.material.tabs.TabLayout
import java.nio.file.Files.find

class Campaign : Fragment(R.layout.fragment_campaign) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCampaignBinding.bind(view)

        val tabLayout = binding.tabLayout

        val donationContainer = view.findViewById<FragmentContainerView>(R.id.donation_fragment_container)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // handle tab selection event
                when (tab.position) {
                    0 -> {
                        donationContainer.findNavController().popBackStack()
                        donationContainer.findNavController().navigate(R.id.donationListItem)
                    }
                    1 -> {
                        donationContainer.findNavController().navigate(R.id.volunteerListItem)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}