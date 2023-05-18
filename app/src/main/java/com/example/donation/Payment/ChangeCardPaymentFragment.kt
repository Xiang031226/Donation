package com.example.donation.Payment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.CardViewModel
import com.example.donation.R
import com.example.donation.ReusableResource.OpenDialog
import com.example.donation.adapter.CreditCardAdapter
import com.example.donation.databinding.FragmentChangeCardPaymentBinding
import com.example.donation.model.CreditCard

class ChangeCardPaymentFragment : Fragment() {

    private lateinit var binding : FragmentChangeCardPaymentBinding
    private lateinit var viewModel: CardViewModel
    private lateinit var adapter: CreditCardAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[CardViewModel::class.java]
        adapter = CreditCardAdapter(viewModel)
        return inflater.inflate(R.layout.fragment_change_card_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChangeCardPaymentBinding.bind(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.card_payment_recycler_view)
            recyclerView.adapter = adapter
        recyclerView?.setHasFixedSize(true)

        viewModel.readAllData.observe(viewLifecycleOwner) { card ->
            adapter.setData(card)
        }

        binding.apply {
            changePaymentToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
            changePaymentToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            addCardButton.setOnClickListener {
                showDialog()
            }
        }


    }

    private fun showDialog() {
        val popUpFragment = OpenDialog()
        popUpFragment.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
    }

}