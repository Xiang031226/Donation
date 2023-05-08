package com.example.donation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentCheckoutBinding


class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
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