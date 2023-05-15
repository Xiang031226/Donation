package com.example.donation.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentEditProfileBinding

class EditProfileFragment : HideBarOrTab() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomBar()

        binding = FragmentEditProfileBinding.bind(view)

        binding.editProfileToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.editProfileToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        showBottomBar()
    }
}