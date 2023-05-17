package com.example.donation.Payment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.Campaign.Donation.DonationViewModel
import com.example.donation.ReusableResource.OpenDialog
import com.example.donation.R
import com.example.donation.databinding.FragmentCheckoutBinding


class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewModel: DonationViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        val image = view.findViewById<ImageView>(R.id.title_image)
        val title = view.findViewById<TextView>(R.id.title)
        val paymentOption = view.findViewById<TextView>(R.id.payment_option)
        val totalAmount = view.findViewById<TextView>(R.id.total_amount)
        val donationType = view.findViewById<TextView>(R.id.donation_type)

        viewModel = ViewModelProvider(requireActivity())[DonationViewModel::class.java]
        viewModel.selectedAnimal.observe(viewLifecycleOwner) { selectedAnimal ->
            image.setImageResource(selectedAnimal.imageResourceId)
            title.text = selectedAnimal.title
        }

        viewModel.payment.observe(viewLifecycleOwner) { payment ->
            paymentOption.text = payment.paymentMethod
            val amount = payment.amount.toString()
            totalAmount.text = "RM " + if(amount.endsWith(".0")) {"${amount}0"} else {
                amount
            }
            donationType.text = payment.donationType
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCheckoutBinding.bind(view)

        val toolbar = view.findViewById<Toolbar>(R.id.custom_toolbar)
        toolbar.title = "Checkout"

        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editIcon.setOnClickListener{
            showBottomSheet()
        }

        binding.confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_thankYouFragment, bundleOf("fromScreen" to "donation"))
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }

    }

    private fun showBottomSheet() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)

        val creditCardLayout = dialog.findViewById<LinearLayout>(R.id.card_payment_layout)
        val googlePayLayout = dialog.findViewById<LinearLayout>(R.id.google_pay_layout)

        val clickableList = listOf(creditCardLayout, googlePayLayout)

        for (item in clickableList) {
            item.setOnClickListener {
                when(item.id) {
                    R.id.card_payment_layout -> {
                        dialog.dismiss()
                        Toast.makeText(activity, "Card payment is clicked", Toast.LENGTH_SHORT).show()
                        val popUpFragment = OpenDialog()
                        popUpFragment.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
                    }
                    R.id.google_pay_layout -> {
                        dialog.dismiss()
                        Toast.makeText(activity, "Google Pay is clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations  = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

}