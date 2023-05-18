package com.example.donation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.data.Subscription
import com.example.donation.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriptionViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Subscription>>
    private val repository: SubscriptionRepository

    init {
        val subscriptionDao = AppDatabase.getDatabase(application).SubscriptionDao()
        repository = SubscriptionRepository(subscriptionDao)
        readAllData = repository.readAllData
    }

    fun insertSubscription(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSubscription(subscription)
        }
    }

    fun cancelSubscription(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.cancelSubscription(subscription)
        }
    }

    fun getSubscriptionById(id: Int) : LiveData<Subscription> {
        return repository.getSubscriptionById(id)
    }
}