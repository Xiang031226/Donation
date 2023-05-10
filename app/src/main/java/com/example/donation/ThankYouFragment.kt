package com.example.donation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentThankYouBinding

class ThankYouFragment : HideBarOrTab() {

    private lateinit var binding: FragmentThankYouBinding
    private lateinit var fromScreen : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thank_you, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentThankYouBinding.bind(view)
        hideTab()
        hideAppBar()
        hideBottomBar()

        fromScreen = arguments?.getString("fromScreen").toString()
        when (fromScreen) {
            "donation" -> binding.thankyouMsg.text = "Thank you for making a difference!"
            "volunteer" -> binding.thankyouMsg.text = "Thank you, we will reach out to you soon!"
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateToCorrectScreen()
        }

        binding.backButton.setOnClickListener {
            navigateToCorrectScreen()
        }
    }

    private fun navigateToCorrectScreen() {
        if (fromScreen == "donation") {
            findNavController().popBackStack(R.id.donationListItem, false)
        } else {
            findNavController()
                .popBackStack(R.id.volunteerListItem, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showTab()
        showBottomBar()
        showAppBar()
    }

}
