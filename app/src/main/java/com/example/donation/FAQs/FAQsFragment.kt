package com.example.donation.FAQs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.adapter.FaqsCardAdapter
import com.example.donation.data.FaqsSource
import com.example.donation.databinding.FragmentFaqsBinding

class FAQsFragment : Fragment() {

    private lateinit var binding: FragmentFaqsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faqs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFaqsBinding.bind(view)

        binding.faqsToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.faqsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val faqsList = FaqsSource().loadFaqsData()
        val adapter = FaqsCardAdapter(faqsList)
        binding.apply {
            faqsRecyclerView.adapter = adapter
            faqsRecyclerView.setHasFixedSize(true)
        }

    }

}