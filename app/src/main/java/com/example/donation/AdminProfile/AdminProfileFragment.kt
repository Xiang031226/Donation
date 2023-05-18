package com.example.donation.AdminProfile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.AccountViewModel
import com.example.donation.R
import com.example.donation.databinding.FragmentAdminProfileBinding
import com.example.donation.model.User

class AdminProfileFragment : Fragment(R.layout.fragment_admin_profile) {

    private lateinit var binding: FragmentAdminProfileBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_profile, container, false)
        viewModel = ViewModelProvider(requireActivity())[AccountViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminProfileBinding.bind(view)

        val prefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val username = prefs.getString("userName", "").toString()
        val email = prefs.getString("userEmail", "").toString()

        val currentUser = viewModel.getByNameAndEmail(username, email)
        currentUser.observe(viewLifecycleOwner) { user ->
            binding.adminProfileNameText.text = user.name
            binding.adminProfileUsernameText.text = user.username
            binding.adminProfileEmailText.text = user.email
            viewModel.currentUser.value = user
        }

        binding.apply {
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