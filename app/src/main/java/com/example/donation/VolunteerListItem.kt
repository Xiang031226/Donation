package com.example.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.adapter.VolunteerCardAdapter
import com.example.donation.adapter.VolunteerItemClickListener
import com.example.donation.data.VolunteerSource

class VolunteerListItem : Fragment(R.layout.volunteer_activity_list), VolunteerItemClickListener {

    private lateinit var viewModel: VolunteerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.volunteer_activity_list, container, false)

        val eventDescriptionList = VolunteerSource().eventDescriptionList()

        viewModel = ViewModelProvider(requireActivity())[VolunteerViewModel::class.java]

        val adapter = VolunteerCardAdapter(viewModel, eventDescriptionList, this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.volunteer_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onItemClick() {
        findNavController().navigate(R.id.action_volunteerListItem_to_volunteerDescription)
    }
}