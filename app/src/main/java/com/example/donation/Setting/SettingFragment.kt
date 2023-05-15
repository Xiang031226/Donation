package com.example.donation.Setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.databinding.FragmentSettingBinding

class SettingFragment : HideBarOrTab() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        hideBottomBar()
        hideAppBar()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingBinding.bind(view)

        binding.settingToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.settingToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val navView = view.findViewById<DrawerLayout>(R.id.nav_view)

    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomBar()
        showAppBar()
    }

}