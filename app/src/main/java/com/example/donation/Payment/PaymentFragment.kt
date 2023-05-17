package com.example.donation.Payment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.Campaign.Donation.DonationViewModel
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.databinding.FragmentPaymentBinding
import com.example.donation.model.Payment
import java.text.NumberFormat

class PaymentFragment : HideBarOrTab() {

    private lateinit var viewModel : DonationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[DonationViewModel::class.java]
                // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPaymentBinding.bind(view)
        val toolbar = view.findViewById<Toolbar>(R.id.custom_toolbar)
        toolbar.title = "Choose donation type"

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }

        binding.apply {

            val ten = RM10
            val twenty = RM20
            val hundred = RM100
            val twoHundred = RM200
            val threeHundred = RM300
            val fiveHundred = RM500

            val amountSelection =
                listOf(ten, twenty, hundred, twoHundred, threeHundred, fiveHundred)

            for (amount in amountSelection) {
                amount.setOnClickListener {
                    val amountTrim = amount.text.toString().substring(2) + ".00"
                    amountInput.setText(amountTrim)
                }
            }

            amountInput.setOnFocusChangeListener{ _, hasFocus ->

                if(amountInput.text.toString().isEmpty()) return@setOnFocusChangeListener

                if (!hasFocus) {
                    val formatter = NumberFormat.getCurrencyInstance()
                    formatter.maximumFractionDigits = 2
                    val formattedAmount = formatter.format(amountInput.text.toString().toDouble())
                    amountInput.setText(formattedAmount.toString().substring(1), TextView.BufferType.EDITABLE) // to get rid of currency unit
                }
            }
        }

        var donationType: String = ""
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedButton = view.findViewById<RadioButton>(checkedId)
            donationType = checkedButton.text.toString()
        }


        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nextButton.setOnClickListener {
            binding.amountInput.clearFocus()
            val totalAmount = binding.amountInput.text.toString().toDouble()

            viewModel.payment.value = Payment(amount = totalAmount, donationType = donationType)

            findNavController().navigate(R.id.action_paymentFragment_to_checkoutFragment)
        }
    }

}