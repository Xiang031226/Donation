package com.example.donation.Campaign

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.databinding.FragmentCampaignBinding
import com.google.android.material.tabs.TabLayout

class Campaign : Fragment(R.layout.fragment_campaign) {

    private lateinit var tabLayout: TabLayout
    private var lastSelectedTabPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentCampaignBinding.bind(view)
        tabLayout = binding.tabLayout

        tabLayout.getTabAt(lastSelectedTabPosition)?.select()

        val donationContainer =
            view.findViewById<FragmentContainerView>(R.id.donation_fragment_container)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // handle tab selection event
                when (tab.position) {
                    0 -> {
                        donationContainer.findNavController().navigate(R.id.donationListItem)
                    }
                    1 -> {
                        donationContainer.findNavController().navigate(R.id.volunteerListItem)
                    }
                }

                lastSelectedTabPosition = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Log.d("Msg : ", "fuck")
                requireActivity().onBackPressedDispatcher.addCallback(this@Campaign) {
                    if (lastSelectedTabPosition == 0) {
                        findNavController().popBackStack(R.id.home, false)
                        Log.d("Msg : ", "hmm")
                    } else {
                        tabLayout.getTabAt(0)?.select()
                        donationContainer.findNavController()
                            .popBackStack(R.id.volunteerListItem, true)
                        Log.d("Msg : ", "hmm2")
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                var recyclerView: RecyclerView? = null
                when (tab.position) {
                    0 -> {
                        recyclerView = view.findViewById(R.id.donation_recycler_view)
                    }
                    1 -> {
                        recyclerView = view.findViewById(R.id.volunteer_recycler_view)
                    }
                }
                recyclerView?.smoothScrollToPosition(0)
            }
        })


        requireActivity().onBackPressedDispatcher.addCallback(this@Campaign) {
            findNavController().popBackStack(R.id.home, false)
        }
    }
}