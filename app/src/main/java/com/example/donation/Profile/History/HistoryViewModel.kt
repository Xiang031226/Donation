package com.example.donation.Profile.History

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.donation.model.History

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val historyLiveData = MutableLiveData<List<History>>()
}
