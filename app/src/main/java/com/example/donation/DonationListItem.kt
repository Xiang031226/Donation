package com.example.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.adapter.DonationCardAdapter
import com.example.donation.adapter.DonationItemClickListener
import com.example.donation.data.DonationSource
import com.example.donation.databinding.DonationActivityListBinding


class DonationListItem() : Fragment(), DonationItemClickListener {

    private lateinit var binding: DonationActivityListBinding
    private lateinit var viewModel: DonationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //inflate the view first, like make a copy of code I guess
        val view = inflater.inflate(R.layout.donation_activity_list, container, false)

        //get the instance of my class that have data, so here wont be bunch of code lol
        val animalDescriptionList = DonationSource(requireContext()).loadAnimalDescriptionData()

        viewModel = ViewModelProvider(requireActivity())[DonationViewModel::class.java]

        //initialize my adapter
        val adapter = DonationCardAdapter(viewModel, animalDescriptionList, this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.donation_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DonationActivityListBinding.bind(view)

    }

    //overriding the fun from my adapter that listen to my click, the interface there
    override fun onItemClick() {
        findNavController().navigate(R.id.action_donationListItem_to_descriptionFragment)
    }

}