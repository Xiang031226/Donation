package com.example.donation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentAdminDonationDescBinding

class AdminDonationDescFragment : HideBarOrTab() {

    private lateinit var binding: FragmentAdminDonationDescBinding
    private lateinit var viewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_donation_desc, container, false)

        val titleImage = view.findViewById<ImageView>(R.id.donation_title_image)
        val image2 = view.findViewById<ImageView>(R.id.donation_image2)
        val image3 = view.findViewById<ImageView>(R.id.donation_image3)
        val extTitle = view.findViewById<TextView>(R.id.donation_extinction_title)
        val threatTitle = view.findViewById<TextView>(R.id.donation_threat_title)
        val mustDoTitle = view.findViewById<TextView>(R.id.donation_mustDo_title)
        val awareness = view.findViewById<TextView>(R.id.donation_awareness)
        val threatMsg = view.findViewById<TextView>(R.id.donation_threat_msg)
        val mustDoMsg = view.findViewById<TextView>(R.id.donation_mustDo_msg)
        val supportMsg = view.findViewById<TextView>(R.id.donation_support_msg)
        val topBarTitle = view.findViewById<ConstraintLayout>(R.id.admin_donation_top_bar)
            .findViewById<TextView>(R.id.admin_custom_top_bar_title)

        viewModel = ViewModelProvider(requireActivity())[AnimalViewModel::class.java]
        viewModel.selectedAnimal.observe(viewLifecycleOwner) { selectedAnimal ->
            titleImage.setImageResource(selectedAnimal.titleImage)
            image2.setImageResource(selectedAnimal.image2)
            image3.setImageResource(selectedAnimal.image3)
            extTitle.text = selectedAnimal.extinctionTitle
            threatTitle.text = selectedAnimal.threatTitle
            mustDoTitle.text = selectedAnimal.mustDoTitle
            awareness.text = selectedAnimal.awareness
            threatMsg.text = selectedAnimal.threatMsg
            mustDoMsg.text = selectedAnimal.mustDoMsg
            supportMsg.text = selectedAnimal.supportMsg
            topBarTitle.text = selectedAnimal.title
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAppBar()
        hideTab(R.id.campaign_tab_layout)
        hideBottomBar(R.id.admin_bottom_nav_bar)
        binding = FragmentAdminDonationDescBinding.bind(view)

        binding.apply {
            adminDonationTopBar.adminBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            val initialScrollY = adminDonationDescScroll.scrollY
            adminDonationDescScroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY == initialScrollY) {
                    // The scroll has returned to the initial position
                    adminDonationTopBarHl.visibility = View.GONE
                } else {
                    adminDonationTopBarHl.visibility = View.VISIBLE
                }
            }
        }
        //Back pressed it will navigate back to the Volunteer main fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@AdminDonationDescFragment) {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
        showTab(R.id.campaign_tab_layout)
        showBottomBar(R.id.admin_bottom_nav_bar)
    }
}