package com.example.donation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.Campaign.Donation.DonationViewModel
import com.example.donation.adapter.DonationCardAdapter
import com.example.donation.adapter.DonationItemClickListener
import com.example.donation.data.DonationSource
import com.example.donation.model.Donation

class AdminDonationListItem : Fragment(R.layout.fragment_admin_donation_list_item),
    DonationItemClickListener {

    private lateinit var viewModel: DonationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.admin_donation_activity_list, container, false)

        val animalDescriptionList = DonationSource(requireContext()).loadAnimalDescriptionData()

        viewModel = ViewModelProvider(requireActivity())[DonationViewModel::class.java]

        //Last parameter used to change the button text
        val adapter = DonationCardAdapter(viewModel, animalDescriptionList, this, "View")

        val recyclerView = view.findViewById<RecyclerView>(R.id.admin_donation_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(this@AdminDonationListItem) {
            requireActivity().findNavController(R.id.fcv_admin).popBackStack(R.id.dashboard_fragment, false)
        }
    }

    override fun onItemClick() {
        findNavController().navigate(R.id.action_admin_donation_fragment_to_admin_donation_desc_fragment)
    }
}