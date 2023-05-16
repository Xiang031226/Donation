package com.example.donation.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.Profile.History.HistoryViewModel
import com.example.donation.Profile.MonthlyDonation.MonthlyDonationMessageFragment
import com.example.donation.Profile.MonthlyDonation.MonthlyDonationProgressFragment
import com.example.donation.R
import com.example.donation.adapter.HistoryCardAdapter
import com.example.donation.data.HistorySource
import com.example.donation.databinding.FragmentProfileBinding
import com.google.android.material.appbar.AppBarLayout

open class Profile : HideBarOrTab() {

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        hideAppBar()

        if (1 == 2) {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.monthly_donation_container, MonthlyDonationMessageFragment())
                    .commit()
            }
        } else {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.monthly_donation_container, MonthlyDonationProgressFragment())
                    .commit()
            }
        }

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.profile_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
        }

        val historyList = HistorySource(requireContext()).loadHistoryData()
        val viewModel = ViewModelProvider(requireActivity())[HistoryViewModel::class.java]
        val recyclerView = view.findViewById<RecyclerView>(R.id.history_recycler_view)
        val adapter = HistoryCardAdapter(historyList)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = adapter

        binding = FragmentProfileBinding.bind(view)

        binding.showAllHistoryButton.setOnClickListener {
            viewModel.historyLiveData.value = historyList
            findNavController().navigate(R.id.action_profile_to_historyFragment)
        }

        binding.editIcon.setOnClickListener{
            findNavController().navigate(R.id.action_profile_to_editProfileFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
    }
}