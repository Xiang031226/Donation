package com.example.donation

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.donation.R.layout.fragment_application
import com.example.donation.adapter.ApplicationItemAdapter
import com.example.donation.databinding.FragmentApplicationBinding

class ApplicationFragment : Fragment(fragment_application), AdapterView.OnItemSelectedListener {
    private lateinit var applicationBinding: FragmentApplicationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_application, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applicationBinding = FragmentApplicationBinding.bind(view)

        val campaignList = ArrayList<String>()
        campaignList.add("Save Our Tiger")
        campaignList.add("Enhance anti-poaching and trafficking")
        campaignList.add("Save Our Elephant")

        val spinnerAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_item, campaignList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        applicationBinding.campaignSpinner.adapter = spinnerAdapter

        applicationBinding.applicationRecyclerView.apply {
            adapter = ApplicationItemAdapter(context)
            setHasFixedSize(true)
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