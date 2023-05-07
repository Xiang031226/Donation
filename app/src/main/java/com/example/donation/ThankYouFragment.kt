package com.example.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentThankYouBinding

class ThankYouFragment : HideBarOrTab() {

    private lateinit var binding: FragmentThankYouBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thank_you, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentThankYouBinding.bind(view)
        hideTab()
        hideAppBar()
        hideBottomBar()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_thankYouFragment_to_donationListItem2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showTab()
        showBottomBar()
        showAppBar()
    }



}