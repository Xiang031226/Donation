package com.example.donation.Setting

import android.annotation.SuppressLint
import android.app.*
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.donation.NotificationScheduler.MyNotificationReceiver
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.databinding.FragmentSettingBinding
import java.util.*


class SettingFragment : HideBarOrTab() {

    private lateinit var binding: FragmentSettingBinding
    private var notificationPendingIntent: PendingIntent? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        hideBottomBar()
        hideAppBar()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    @SuppressLint("ShortAlarm")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingBinding.bind(view)

        binding.settingToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.settingToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.faqTextview.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_FAQsFragment)
        }

        binding.aboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }

        binding.privacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_privacyPolicyFragment)
        }

        binding.changePasswordButton.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_changePasswordFragment) }
        binding.addCardButton.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_changeCardPaymentFragment) }
        binding.rateUs.setOnClickListener {
            launchAppReview()
        }
        binding.contactUs.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:support@wildcare.com")
            }

            if (activity?.let { it1 -> emailIntent.resolveActivity(it1.packageManager) } != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(activity, "No email app found.", Toast.LENGTH_SHORT).show()
            }
        }

        val preferences = context?.getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE)
        val counter = preferences?.getInt("notificationCounter", 0)

        if (counter != null) {
            binding.notificationToggler.isChecked = counter < 12
            binding.notificationToggler.jumpDrawablesToCurrentState()
        }
        binding.notificationToggler.setOnCheckedChangeListener { _, isChecked ->
            handleToggleChecked(isChecked)
        }
        handleToggleChecked(binding.notificationToggler.isChecked)
    }

    private fun handleToggleChecked(isChecked : Boolean) {
        Toast.makeText(activity, "??", Toast.LENGTH_SHORT).show()
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(activity, MyNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        if (isChecked) {
//                val calendar = Calendar.getInstance()
//                calendar.timeInMillis = System.currentTimeMillis()
//                calendar.add(Calendar.DAY_OF_MONTH, 30)
//
//                val intervalMillis = 30 * 24 * 60 * 60 * 1000L
//
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, intervalMillis, pendingIntent)

            val intervalMillis = 5 * 1000L // 5 seconds

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalMillis, pendingIntent)
        } else {
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun launchAppReview() {
        val appPackageName = "com.sst.panda&hl" // Replace with your app's package name

        try {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomBar()
        showAppBar()
    }

}