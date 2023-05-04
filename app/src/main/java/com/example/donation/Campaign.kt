package com.example.donation

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.databinding.FragmentCampaignBinding
import com.google.android.material.tabs.TabLayout

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
        replaceFragment(DonationListItem(), "Donation")

        val binding = FragmentCampaignBinding.bind(view)

        val tabLayout = binding.tabLayout

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // handle tab selection event
                when (tab.position) {
                    0 -> {
                        replaceFragment(DonationListItem(), "Donation")
                    }
                    1 -> {
                        replaceFragment(Volunteer(), "Volunteer")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun replaceFragment(fragment: Fragment, tag:String) {

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, tag)
            addToBackStack(null)
            commit()
        }
    }

}