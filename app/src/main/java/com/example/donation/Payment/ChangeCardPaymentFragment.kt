package com.example.donation.Payment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.ReusableResource.OpenDialog
import com.example.donation.adapter.CreditCardAdapter
import com.example.donation.data.CreditCardSource
import com.example.donation.databinding.FragmentChangeCardPaymentBinding
import com.example.donation.model.CreditCard

class ChangeCardPaymentFragment : Fragment() {

    private lateinit var binding : FragmentChangeCardPaymentBinding
    private lateinit var viewModel: PaymentViewModel
    private lateinit var adapter: CreditCardAdapter
    private var cardLists: MutableList<CreditCard> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[PaymentViewModel::class.java]
        viewModel.dialogData.observe(viewLifecycleOwner){ data ->
            val cardNumber = data.cardNumber
            val expiredDate = data.expiredDate
            val cvv = data.cvv
            cardLists.add(CreditCard(R.drawable.ic_card, cardNumber, expiredDate, cvv, R.drawable.ic_delete))
            adapter.notifyDataSetChanged()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_card_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChangeCardPaymentBinding.bind(view)

        adapter = CreditCardAdapter(requireContext(), cardLists)
        val recyclerView = view.findViewById<RecyclerView>(R.id.card_payment_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

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