package com.example.donation.Profile.MonthlyDonation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.SubscriptionViewModel
import com.example.donation.data.Subscription
import com.example.donation.databinding.FragmentMonthlyDonationProgressBinding

class MonthlyDonationProgressFragment : Fragment() {

    private lateinit var binding: FragmentMonthlyDonationProgressBinding
    private lateinit var title : String
    private var progress = 0
    private var amount = 0.00
    private lateinit var subVwModel : SubscriptionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        title = arguments?.getString("title").toString()
        progress = arguments?.getInt("progress")!!
        amount = arguments?.getDouble("amount")!!
        Log.e("Msg : ", "$title + $progress + $amount")

        return inflater.inflate(R.layout.fragment_monthly_donation_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMonthlyDonationProgressBinding.bind(view)

        val progressBar = binding.progressBar
        progressBar.max = 1200

        val currentProgress = progress
        progressBar.progress = currentProgress

        binding.donationAmount.text = amount.toString()
        binding.donationGoal.text = title
        binding.currentMonth.text = (currentProgress / 100).toString()

        binding.monthlyDonatedAmount.text = "RM " + amount.toString() + " donated"

        binding.certificateTextview.setOnClickListener {
                findNavController().navigate(R.id.action_profile_to_digitalCertificateFragment)
//            else {
//                val preferences = requireActivity().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
//                val userId = preferences.getInt("userId", -1)
//                subVwModel = ViewModelProvider(requireActivity())[SubscriptionViewModel::class.java]
//                    val subscription = Subscription(
//                        userId = userId,
//                        donationGoal = title,
//                        amount = amount,
//                        paymentMethod = binding.paymentMethod.text.toString(),
//                        progress = currentProgress
//                    )
//                subVwModel.cancelSubscription(subscription)
//            }

        }
    }

}