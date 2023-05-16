package com.example.donation.Campaign.Volunteer

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.R
import com.example.donation.databinding.FragmentVolunteerDescriptionBinding

class VolunteerDescription : HideBarOrTab() {

    private lateinit var binding: FragmentVolunteerDescriptionBinding
    private lateinit var viewModel: VolunteerViewModel

    @SuppressLint("DiscouragedApi", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_volunteer_description, container, false)

        val eventImage = view.findViewById<ImageView>(R.id.event_image)
        val eventTitle = view.findViewById<TextView>(R.id.event_title)
        val eventDate = view.findViewById<TextView>(R.id.date_text)
        val eventTime = view.findViewById<TextView>(R.id.time_text)
        val eventLocation = view.findViewById<TextView>(R.id.location_text)
        val roleContainer = view.findViewById<LinearLayout>(R.id.radio_group)
        val jobScopeContainer = view.findViewById<LinearLayout>(R.id.job_scopes_container)
        val skillContainer = view.findViewById<LinearLayout>(R.id.skills_container)

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

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val checkedButtonIndex =
                    group.indexOfChild(group.findViewById<RadioButton>(checkedId))

                for (i in skillList.indices) {
                    if (i == checkedButtonIndex) {
                        for (skill in skillList[i]) {
                            skill.visibility = View.VISIBLE
                        }
                        Log.d("Msg : ", "Skills for button $i are visible")
                    }
                    else {
                        for(skill in skillList[i]) {
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

            val firstRadioButton = binding.radioGroup.getChildAt(0) as RadioButton
            firstRadioButton.isChecked = true
            binding.radioGroup.check(firstRadioButton.id)
            binding.radioGroup.jumpDrawablesToCurrentState()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideTab()
        hideBottomBar()
        hideAppBar()

        binding = FragmentVolunteerDescriptionBinding.bind(view)

        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            volunteerButton.setOnClickListener {
                findNavController().navigate(R.id.action_volunteerDescription_to_thankYouFragment,
                    bundleOf("fromScreen" to "volunteer"))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showTab()
        showAppBar()
        showBottomBar()
    }

}