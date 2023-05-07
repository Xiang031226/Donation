package com.example.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentDonationDescriptionBinding
import com.example.donation.databinding.FragmentVolunteerDescriptionBinding

class VolunteerDescription : HideBarOrTab() {

    private lateinit var binding: FragmentVolunteerDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideTab()
        hideBottomBar()
        hideAppBar()

        binding = FragmentVolunteerDescriptionBinding.bind(view)

        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            volunteerButton.setOnClickListener {
                findNavController().navigate(R.id.action_volunteerDescription_to_thankYouFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showTab()
        showAppBar()
        showBottomBar()
    }

}