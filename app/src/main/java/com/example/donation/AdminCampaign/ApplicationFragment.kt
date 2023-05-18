package com.example.donation.AdminCampaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.R.layout.fragment_application
import com.example.donation.adapter.ApplicationItemAdapter
import com.example.donation.data.VolunteerSource
import com.example.donation.databinding.FragmentApplicationBinding
import com.example.donation.model.Application
import com.example.donation.model.VolunteerRole

class ApplicationFragment : Fragment(fragment_application), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentApplicationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_application, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApplicationBinding.bind(view)

        val campaignTitle = ArrayList<String>()
        val applicationList = ArrayList<Application>()
        val volunteerList = VolunteerSource().eventDescriptionList()
        val nameList = listOf("John Doe", "Lim Zhen Xiang", "Lee Vese", "Lim Ah Meng")

        for ((index, volunteer) in volunteerList.withIndex()) {
            campaignTitle.add(volunteer.eventTitle)
            applicationList.add(
                Application(
                    R.drawable.image3,
                    nameList[index],
                    VolunteerSource().volunteerRoles[index].role
                )
            )
        }

        val spinnerAdapter =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, campaignTitle)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.campaignSpinner.adapter = spinnerAdapter

        //Recycler view adapter
        binding.applicationRecyclerView.apply {
            adapter = ApplicationItemAdapter(context, applicationList)
            setHasFixedSize(true)
        }

        //Back pressed it will navigate back to the Volunteer main fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@ApplicationFragment) {
            findNavController().popBackStack()
        }
    }

    //Spinner methods
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}