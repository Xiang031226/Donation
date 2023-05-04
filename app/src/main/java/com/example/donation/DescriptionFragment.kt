package com.example.donation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.databinding.FragmentDescriptionBinding

class DescriptionFragment : HideBarOrTab() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomBar()
        hideTab()
        hideAppBar()

        val binding = FragmentDescriptionBinding.bind(view)

        binding.apply {
            donateButton.setOnClickListener {
                Log.d("Msg", "Hmm")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showAppBar()
        showBottomBar()
        showTab()
    }
}