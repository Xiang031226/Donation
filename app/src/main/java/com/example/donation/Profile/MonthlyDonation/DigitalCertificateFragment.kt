package com.example.donation.Profile.MonthlyDonation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.databinding.FragmentDigitalCertificateBinding

class DigitalCertificateFragment : HideBarOrTab() {

    private lateinit var binding: FragmentDigitalCertificateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_digital_certificate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomBar()

        binding = FragmentDigitalCertificateBinding.bind(view)

        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomBar()
    }
}