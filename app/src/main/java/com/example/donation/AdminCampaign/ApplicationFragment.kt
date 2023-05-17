package com.example.donation.AdminCampaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.R.layout.fragment_application
import com.example.donation.adapter.ApplicationItemAdapter
import com.example.donation.databinding.FragmentApplicationBinding
import com.example.donation.model.Application

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

        // TODO: Data for campaign spinner
        val campaignList = ArrayList<String>()
        campaignList.add("Save Our Tiger")
        campaignList.add("Enhance anti-poaching and trafficking")
        campaignList.add("Save Our Elephant")

        // TODO: Data for volunteer application recycler view
        var applicationList = arrayListOf(
            Application(
                R.drawable.image1,
                "Lim Zhen Xiang",
                "Tiger Caretaker"
            ),
            Application(
                R.drawable.image1,
                "Lee Vese",
                "Elephant Caretaker"
            ),
            Application(
                R.drawable.image1,
                "Lim Zhen Xiang",
                "Tiger Caretaker"
            ),
            Application(
                R.drawable.image1,
                "Lee Vese",
                "Elephant Caretaker"
            )
        )

        val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, campaignList)
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