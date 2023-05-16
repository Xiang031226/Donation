package com.example.donation.AdminProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.Campaign.Volunteer.VolunteerViewModel
import com.example.donation.R
import com.example.donation.adapter.VolunteerCardAdapter
import com.example.donation.adapter.VolunteerItemClickListener
import com.example.donation.data.VolunteerSource

class AdminVolunteerListItem : Fragment(R.layout.fragment_admin_volunteer_list_item), VolunteerItemClickListener {

    private lateinit var viewModel: VolunteerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.volunteer_activity_list, container, false)

        val eventDescriptionList = VolunteerSource().eventDescriptionList()

        viewModel = ViewModelProvider(requireActivity())[VolunteerViewModel::class.java]

        //Last parameter used to change the button text
        val adapter = VolunteerCardAdapter(viewModel, eventDescriptionList, this, "View")

        val recyclerView = view.findViewById<RecyclerView>(R.id.volunteer_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onItemClick() {
        //Navigate to Edit Volunteer fragment
        findNavController().navigate(R.id.action_admin_volunteer_fragment_to_admin_volunteer_desc_fragment)
    }
}