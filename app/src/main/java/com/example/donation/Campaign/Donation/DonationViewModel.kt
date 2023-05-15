package com.example.donation.Campaign.Donation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.donation.model.Donation

class DonationViewModel : ViewModel() {
    val selectedAnimal = MutableLiveData<Donation>()
}