package com.example.donation.AboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentAboutUsBinding

class AboutUsFragment : HideBarOrTab() {

    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        hideBottomBar()
        hideAppBar()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutUsBinding.bind(view)

        binding.aboutUsToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.aboutUsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
        showBottomBar()
    }

}