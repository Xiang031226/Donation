package com.example.donation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.adapter.DonationCardAdapter
import com.example.donation.adapter.RecyclerViewItemClickListener
import com.example.donation.databinding.DonationActivityListBinding

class DonationListItem() : Fragment(), RecyclerViewItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.donation_activity_list, container, false)

        // Inflate the layout for this fragment
        val recyclerView = view.findViewById<RecyclerView>(R.id.donation_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DonationCardAdapter(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DonationActivityListBinding.bind(view)

        binding.donationRecyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(fragment: Fragment) {
        // Switch to the desired fragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Msg", "Hello")
    }
}