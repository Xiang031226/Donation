package com.example.donation.Campaign.Volunteer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.donation.model.Volunteer

class VolunteerViewModel() : ViewModel() {

    val selectedEvent = MutableLiveData<Volunteer>()
//    val volunteerData: LiveData<Volunteer> = _volunteerData
//
//    fun loadVolunteerData(volunteerEvent: String) {
//    }

}