package com.example.donation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _sharedData = MutableLiveData<String>()
    val sharedData: LiveData<String> = _sharedData

    fun setSharedData(data: String) {
        _sharedData.value = data
    }
}
