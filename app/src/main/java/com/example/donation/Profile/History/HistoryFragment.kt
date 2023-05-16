package com.example.donation.Profile.History

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.adapter.HistoryCardAdapter
import com.example.donation.adapter.HistoryItemClickListener
import com.example.donation.databinding.FragmentHistoryBinding

class HistoryFragment : HideBarOrTab() {

    private lateinit var binding: FragmentHistoryBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val viewModel = ViewModelProvider(requireActivity())[HistoryViewModel::class.java]
        val recyclerView = view.findViewById<RecyclerView>(R.id.history_container)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        val adapter = HistoryCardAdapter(emptyList())
        adapter.setOnItemClickListener(object : HistoryItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(activity, "Okay", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        adapter.setShowAllItems(true)

        viewModel.historyLiveData.observe(viewLifecycleOwner) {historyList ->
            adapter.updateList(historyList)
        }

        hideBottomBar()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHistoryBinding.bind(view)

        binding.historyToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.historyToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showBottomBar()
    }
}