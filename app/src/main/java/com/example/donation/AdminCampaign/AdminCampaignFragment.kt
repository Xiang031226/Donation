package com.example.donation.AdminCampaign

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.databinding.FragmentAdminCampaignBinding
import com.google.android.material.tabs.TabLayout

open class AdminCampaignFragment : Fragment(R.layout.fragment_admin_campaign) {

    private lateinit var campaignTabLayout: TabLayout
    private var lastSelectedTabPosition = 0
    private lateinit var binding: FragmentAdminCampaignBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminCampaignBinding.bind(view)

        //Donation and Volunteer fragment nav controller
        val campaignContainer = childFragmentManager.findFragmentById(R.id.fcv_campaign)
        val campaignNavController = campaignContainer!!.findNavController()

        //Campaign tab layout
        campaignTabLayout = binding.campaignTabLayout

        //by default, the Donation tab is selected (position 0)
        campaignTabLayout.getTabAt(lastSelectedTabPosition)?.select()
        campaignNavController.navigate(R.id.admin_donation_fragment)

        binding.apply {
            fabAdd.setOnClickListener {
                //Navigate to Create Volunteer fragment
                campaignNavController.navigate(R.id.action_admin_volunteer_fragment_to_create_volunteer_fragment)
            }
        }

        //Campaign fragment listener
        campaignNavController.addOnDestinationChangedListener { _, destination, _ ->
            //Set the visibility of add button based on the current destination
            if (destination.id == R.id.admin_volunteer_fragment) {
                binding.fabAdd.visibility = View.VISIBLE
            } else {
                binding.fabAdd.visibility = View.GONE
            }
        }

        //Campaign tab layout listener
        campaignTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        //Donation tab selected
                        Log.i("OnTabSelected", "Donation")
                        campaignNavController.navigate(R.id.admin_donation_fragment)
                    }
                    1 -> {
                        //Volunteer tab selected
                        Log.i("OnTabSelected", "Volunteer")
                        campaignNavController.navigate(R.id.admin_volunteer_fragment)
                    }
                }
                lastSelectedTabPosition = tab.position
                Log.i("lastSelectedTabPosition", lastSelectedTabPosition.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Log.i("onTabUnselected", "im in onTabUnselected")
                requireActivity().onBackPressedDispatcher.addCallback(this@AdminCampaignFragment) {
                    if (lastSelectedTabPosition == 0) {
                        Log.i("onTabUnselected", "lastSelectedTabPosition is 0")
                        Log.i("View?", view.toString())
                        findNavController().popBackStack(R.id.dashboard_fragment, false)
                    } else {
                        Log.i("onTabUnselected", "lastSelectedTabPosition not 0")
                        campaignTabLayout.getTabAt(0)?.select()
                        campaignNavController.popBackStack(R.id.admin_volunteer_fragment, true)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                var recyclerView: RecyclerView? = null
                when (tab.position) {
                    0 -> {
                        Log.i("onTabReselected", "Donation")
                        recyclerView = view.findViewById(R.id.donation_recycler_view)
                    }
                    1 -> {
                        Log.i("onTabReselected", "Volunteer")
                        recyclerView = view.findViewById(R.id.volunteer_recycler_view)
                    }
                }
                recyclerView?.smoothScrollToPosition(0)
            }
        })
    }
}
