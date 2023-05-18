package com.example.donation.Payment

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.*
import com.example.donation.Campaign.Donation.DonationViewModel
import com.example.donation.ReusableResource.OpenDialog
import com.example.donation.data.DonationTransaction
import com.example.donation.data.Subscription
import com.example.donation.database.AppDatabase
import com.example.donation.databinding.FragmentCheckoutBinding


class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var usViewModel: AccountViewModel
    private lateinit var viewModel: DonationViewModel
    private lateinit var cardVwModel: CardViewModel
    private lateinit var subVWModel: SubscriptionViewModel
    private var paymentMethod: String = "Google Pay"
    private lateinit var category: String
    private lateinit var amount: String

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
            category = selectedAnimal.category
        }

        viewModel.payment.observe(viewLifecycleOwner) { payment ->
            paymentOption.text = payment.paymentMethod
            amount = payment.amount.toString()
            totalAmount.text = "RM " + if (amount.endsWith(".0")) {
                "${amount}0"
            } else {
                amount
            }
            donationType.text = payment.donationType
        }

        cardVwModel = ViewModelProvider(requireActivity())[CardViewModel::class.java]
        cardVwModel.getCardById(1).observe(viewLifecycleOwner) { card ->
            if (card != null) {
                binding.paymentOption.text = card.cvv.toString()
            }
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCheckoutBinding.bind(view)

        val toolbar = view.findViewById<Toolbar>(R.id.custom_toolbar)
        toolbar.title = "Checkout"

        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editIcon.setOnClickListener {
            showBottomSheet()
        }

        binding.confirmButton.setOnClickListener {
            insertTransactionToDatabase()

            findNavController().navigate(R.id.action_checkoutFragment_to_thankYouFragment,
                bundleOf("fromScreen" to "donation"))
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertTransactionToDatabase() {
        val currentDateTime = LocalDateTime.now()

        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

        val formattedDate = currentDateTime.format(dateFormatter)
        val formattedTime = currentDateTime.format(timeFormatter)

        val donationTitle = binding.title.text.toString()
        val amount = amount.toDouble()
        val donationType = binding.donationType.text.toString()
        val paymentMethod = this.paymentMethod
        val category = this.category

        val prefs = requireActivity().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        val userId = prefs.getInt("userId", -1)

        if (userId != -1) {
            val transaction = DonationTransaction(
                userId = userId,
                donationTitle = donationTitle,
                amount = amount,
                date = formattedDate,
                time = formattedTime,
                donationType = donationType,
                paymentMethod = paymentMethod,
                category = category
            )
            viewModel.addTransaction(transaction)

            if (donationType == "Monthly") {
                subVWModel = ViewModelProvider(requireActivity())[SubscriptionViewModel::class.java]
                val subscription = Subscription(userId = userId,
                    donationGoal = donationTitle,
                    amount = amount,
                    paymentMethod = paymentMethod,
                    progress = 100
                )
                subVWModel.insertSubscription(subscription)
            }
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
                when (item.id) {
                    R.id.card_payment_layout -> {
                        dialog.dismiss()
                        Toast.makeText(activity, "Card payment is clicked", Toast.LENGTH_SHORT)
                            .show()
                        val popUpFragment = OpenDialog()
                        popUpFragment.show((activity as AppCompatActivity).supportFragmentManager,
                            "showPopUp")
                        paymentMethod = "Credit Card"
                    }
                    R.id.google_pay_layout -> {
                        dialog.dismiss()
                        Toast.makeText(activity, "Google Pay is clicked", Toast.LENGTH_SHORT).show()
                        paymentMethod = "Google Pay"
                    }
                }
            }
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

}