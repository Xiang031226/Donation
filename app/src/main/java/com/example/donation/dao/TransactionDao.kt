package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.data.DonationTransaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM donationTransaction")
    fun readAllData(): LiveData<List<DonationTransaction>>

    @Query("SELECT * FROM donationTransaction WHERE userId = :userId")
    fun getTransactionByUserId(userId: Int) : LiveData<List<DonationTransaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transaction: DonationTransaction)

    @Query("SELECT * FROM donationTransaction WHERE userId = :userId")
    fun getTransactionById(userId: Int): LiveData<List<DonationTransaction>>

    @Query("SELECT SUM(amount) FROM donationTransaction WHERE userId = :userId")
    fun getTotalDonation(userId: Int) : LiveData<Double>
}