package com.example.donation.Profile.MonthlyDonation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.databinding.FragmentMonthlyDonationProgressBinding

class MonthlyDonationProgressFragment : Fragment() {

    private lateinit var binding: FragmentMonthlyDonationProgressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monthly_donation_progress, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.max = 1200

        val currentProgress = 700
        progressBar.progress = currentProgress

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMonthlyDonationProgressBinding.bind(view)

        binding.cancelOrClaimCertificateButton.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_digitalCertificateFragment)
        }
    }

}