package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.data.Subscription

@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription")
    fun readAllData(): LiveData<List<Subscription>>

    @Query("SELECT * FROM subscription WHERE userId = :userId")
    fun getSubscriptionByUserId(userId : Int) : LiveData<Subscription>

    @Delete
    suspend fun cancelSubscription(subscription: Subscription)

    @Update
    suspend fun updateSubscription(subscription: Subscription)

    @Insert
    suspend fun insertSubscription(subscription: Subscription)
}