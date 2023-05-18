package com.example.donation

import androidx.lifecycle.LiveData
import com.example.donation.dao.SubscriptionDao
import com.example.donation.dao.TransactionDao
import com.example.donation.data.DonationTransaction
import com.example.donation.data.Subscription

class SubscriptionRepository(private val subcriptionDao: SubscriptionDao) {

    val readAllData: LiveData<List<Subscription>> = subcriptionDao.readAllData()

    suspend fun insertSubscription(subscription: Subscription) {
        subcriptionDao.insertSubscription(subscription)
    }

    suspend fun cancelSubscription(subscription: Subscription) {
        subcriptionDao.cancelSubscription(subscription)
    }

    fun getSubscriptionById(id: Int) : LiveData<Subscription> {
        return subcriptionDao.getSubscriptionByUserId(id)
    }

}