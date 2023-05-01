package com.example.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.donation.databinding.LoginFragmentBinding
import com.example.donation.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private lateinit var registerFragmentBinding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        registerFragmentBinding = RegisterFragmentBinding.inflate(inflater, container, false)
        return registerFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerFragmentBinding.registerButton.setOnClickListener {
            backToLoginFragment()
        }
    }

    private fun backToLoginFragment() {
        Toast.makeText(activity, "You are registered. Login again", Toast.LENGTH_SHORT).show()

        // This method closes the current fragment and goes back to the previous fragment
        val fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
}