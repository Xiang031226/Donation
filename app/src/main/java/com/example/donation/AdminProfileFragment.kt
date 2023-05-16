package com.example.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentAdminProfileBinding

class AdminProfileFragment : Fragment(R.layout.fragment_admin_profile) {

    private lateinit var binding: FragmentAdminProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminProfileBinding.bind(view)

        binding.apply{
            adminProfileTopBar.apply {
                adminCustomTopBarTitle.text = "View Profile"
                adminBackButton.apply {
                    visibility = View.INVISIBLE
                    isClickable = false
                }
            }
            adminProfileEditButton.setOnClickListener {
                findNavController().navigate(R.id.action_admin_profile_fragment_to_admin_edit_profile_fragment)
            }
        }
    }
}