package com.example.donation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.donation.model.Description

class AnimalViewModel : ViewModel() {
    val selectedAnimal = MutableLiveData<Description>()
}