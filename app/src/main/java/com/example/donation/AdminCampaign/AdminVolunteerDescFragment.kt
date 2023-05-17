package com.example.donation.AdminCampaign

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.Campaign.Volunteer.VolunteerViewModel
import com.example.donation.R
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentAdminVolunteerDescBinding

class AdminVolunteerDescFragment : HideBarOrTab() {

    private lateinit var binding: FragmentAdminVolunteerDescBinding
    private lateinit var viewModel: VolunteerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_volunteer_desc, container, false)

        val eventImage = view.findViewById<ImageView>(R.id.vol_desc_event_image)
        val eventTitle = view.findViewById<TextView>(R.id.vol_desc_event_title)
        val eventDate = view.findViewById<TextView>(R.id.vol_desc_date_text)
        val eventTime = view.findViewById<TextView>(R.id.vol_desc_time_text)
        val eventLocation = view.findViewById<TextView>(R.id.vol_desc_location_text)
        val roleContainer = view.findViewById<LinearLayout>(R.id.vol_desc_radio_group)
        val jobScopeContainer = view.findViewById<LinearLayout>(R.id.vol_desc_job_scopes_container)
        val skillContainer = view.findViewById<LinearLayout>(R.id.vol_desc_skills_container)

        viewModel = ViewModelProvider(requireActivity())[VolunteerViewModel::class.java]
        viewModel.selectedEvent.observe(viewLifecycleOwner) { selectedEvent ->
            eventImage.setImageResource(selectedEvent.eventImage)
            eventTitle.text = selectedEvent.eventTitle
            eventDate.text = selectedEvent.eventDate
            eventTime.text = selectedEvent.eventTime
            eventLocation.text = selectedEvent.eventLocation
            val roleList = selectedEvent.availableVolunteerRole

            for ((index, role) in roleList.withIndex()) {
                val radioButton = RadioButton(requireContext())
                radioButton.id = index + 1
                radioButton.text = role.role
                radioButton.textSize = 16f
                radioButton.setPadding(30, 0, 0, 0)
                roleContainer.addView(radioButton)
            }

            val jobScopeList = mutableListOf<List<TextView>>()
            for (i in roleList.indices) {
                val jobScopes = mutableListOf<TextView>()
                for ((index, jobScope) in roleList[i].jobScopes.withIndex()) {
                    val jobScopeTextView = TextView(requireContext())
                    jobScopeTextView.text = "${index + 1}. $jobScope"
                    jobScopeTextView.textSize = 16f
                    jobScopeTextView.setTextColor(Color.BLACK)
                    jobScopeTextView.setPadding(40, 12, 0, 12)

                    jobScopeContainer.addView(jobScopeTextView)
                    jobScopes.add(jobScopeTextView)
                }
                jobScopeList.add(jobScopes)
            }

            val skillList = mutableListOf<List<TextView>>()
            for (i in roleList.indices) {
                val skills = mutableListOf<TextView>()
                for ((index, skill) in roleList[i].skills.withIndex()) {
                    val skillTextView = TextView(requireContext())
                    skillTextView.text = "${index + 1}. $skill"
                    skillTextView.textSize = 16f
                    skillTextView.setTextColor(Color.BLACK)
                    skillTextView.setPadding(40, 8, 0, 8)

                    skillContainer.addView(skillTextView)
                    skills.add(skillTextView)
                }
                skillList.add(skills)
            }

            binding.volDescRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                val checkedButtonIndex =
                    group.indexOfChild(group.findViewById<RadioButton>(checkedId))
                Log.d("Msg: ", "checkedButtonIndex = $checkedButtonIndex")

                for (i in skillList.indices) {
                    if (i == checkedButtonIndex) {
                        for (skill in skillList[i]) {
                            skill.visibility = View.VISIBLE
                        }
                        Log.d("Msg : ", "Skills for button $i are visible")
                    } else {
                        for (skill in skillList[i]) {
                            skill.visibility = View.GONE
                        }
                        Log.d("Msg : ", "Skills for button $i are hidden")
                    }
                }

                for (i in jobScopeList.indices) {
                    if (i == checkedButtonIndex) {
                        for (jobScope in jobScopeList[i]) {
                            jobScope.visibility = View.GONE
                        }
                    } else {
                        for (jobScope in jobScopeList[i]) {
                            jobScope.visibility = View.VISIBLE
                        }
                    }
                }
            }
            val firstRadioButton =
                binding.volDescRadioGroup.getChildAt(0) as RadioButton
            firstRadioButton.isChecked = true
            binding.volDescRadioGroup.check(firstRadioButton.id)
            binding.volDescRadioGroup.jumpDrawablesToCurrentState()
        }
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAppBar()
        hideTab(R.id.campaign_tab_layout)
        hideBottomBar(R.id.admin_bottom_nav_bar)

        binding = FragmentAdminVolunteerDescBinding.bind(view)

        binding.apply {
            adminVolDescBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            fabEdit.setOnClickListener {
                findNavController().navigate(R.id.action_admin_volunteer_desc_fragment_to_edit_volunteer_fragment)
            }
            volDescApplicationListButton.setOnClickListener {
                findNavController().navigate(R.id.application_fragment)
            }
        }

        //Back pressed it will navigate back to the Volunteer main fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@AdminVolunteerDescFragment) {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
        showTab(R.id.campaign_tab_layout)
        showBottomBar(R.id.admin_bottom_nav_bar)
    }

    override fun onResume() {
        super.onResume()
        hideAppBar()
        hideTab(R.id.campaign_tab_layout)
        hideBottomBar(R.id.admin_bottom_nav_bar)
    }
}