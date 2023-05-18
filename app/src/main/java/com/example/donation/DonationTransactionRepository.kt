package com.example.donation

import androidx.lifecycle.LiveData
import com.example.donation.dao.TransactionDao
import com.example.donation.data.DonationTransaction


class DonationTransactionRepository(private val transactionDao: TransactionDao) {

    val readAllData: LiveData<List<DonationTransaction>> = transactionDao.readAllData()

    suspend fun addTransaction(transaction: DonationTransaction) {
        transactionDao.addTransaction(transaction)
    }

    fun getTotalDonation(id: Int) : LiveData<Double> {
        return transactionDao.getTotalDonation(id)
    }

    fun getTransactionById(id: Int) : LiveData<List<DonationTransaction>> {
        return transactionDao.getTransactionById(id)
    }

}