package com.example.donation.Profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.AccountViewModel
import com.example.donation.Campaign.Donation.DonationViewModel
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.Profile.History.HistoryViewModel
import com.example.donation.Profile.MonthlyDonation.MonthlyDonationMessageFragment
import com.example.donation.Profile.MonthlyDonation.MonthlyDonationProgressFragment
import com.example.donation.R
import com.example.donation.SubscriptionViewModel
import com.example.donation.adapter.HistoryCardAdapter
import com.example.donation.data.DonationTransaction
import com.example.donation.data.HistorySource
import com.example.donation.databinding.FragmentProfileBinding
import com.google.android.material.appbar.AppBarLayout

open class Profile : HideBarOrTab() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: DonationViewModel
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var accViewModel: AccountViewModel
    private lateinit var subVwModel: SubscriptionViewModel
    private lateinit var preferences: SharedPreferences
    private var userId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment
        hideAppBar()

        accViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        preferences = requireActivity().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        userId = preferences.getInt("userId", -1)

        val username = view.findViewById<TextView>(R.id.username)
        val useremail = view.findViewById<TextView>(R.id.user_email)
        accViewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            username.text = user.username
            useremail.text = user.email
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.profile_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding = FragmentProfileBinding.bind(view)
        subVwModel = ViewModelProvider(requireActivity())[SubscriptionViewModel::class.java]
        subVwModel.getSubscriptionById(userId).observe(viewLifecycleOwner) { subscription ->
            if (subscription != null) {
                val bundle = Bundle()
                bundle.putString("title", subscription.donationGoal)
                bundle.putInt("progress", subscription.progress)
                bundle.putDouble("amount", subscription.amount)
                val fragment = MonthlyDonationProgressFragment()
                fragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.monthly_donation_container,
                        fragment
                    )
                    commit()
                }
            }
            else {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.monthly_donation_container,
                        MonthlyDonationMessageFragment()
                    )
                    commit()
                }
            }
        }


        val historyViewModel = ViewModelProvider(requireActivity())[HistoryViewModel::class.java]

        val adapter = HistoryCardAdapter()

        viewModel = ViewModelProvider(requireActivity())[DonationViewModel::class.java]
        viewModel.getTransactionById(userId).observe(viewLifecycleOwner) { transaction ->
            adapter.updateList(transaction)
            historyViewModel.historyLiveData.value = transaction
        }
        viewModel.getTotalDonation(userId).observe(viewLifecycleOwner) { totalDonation ->
            val formattedTotalDonation = "MYR " + (totalDonation ?: 0)
            binding.totalDonation.text = formattedTotalDonation
        }


        val recyclerView = view.findViewById<RecyclerView>(R.id.history_recycler_view)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = adapter

        binding = FragmentProfileBinding.bind(view)

        binding.showAllHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_historyFragment)
        }

        binding.editIcon.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_editProfileFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
    }
}