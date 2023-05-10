package com.example.donation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.donation.model.Donation

class DonationViewModel : ViewModel() {
    val selectedAnimal = MutableLiveData<Donation>()
}