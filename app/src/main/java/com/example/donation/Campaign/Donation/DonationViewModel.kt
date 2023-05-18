package com.example.donation.Campaign.Donation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.CardRepository
import com.example.donation.DonationTransactionRepository
import com.example.donation.data.DonationSource
import com.example.donation.data.DonationTransaction
import com.example.donation.database.AppDatabase
import com.example.donation.model.Donation
import com.example.donation.model.Payment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DonationViewModel(application: Application) : AndroidViewModel(application) {
    val selectedAnimal = MutableLiveData<Donation>()
    var payment = MutableLiveData<Payment>()

    val readAllData: LiveData<List<DonationTransaction>>
    private val repository: DonationTransactionRepository

    init {
        val transactionDao = AppDatabase.getDatabase(application).TransactionDao()
        repository = DonationTransactionRepository(transactionDao)
        readAllData = repository.readAllData
    }

    fun addTransaction(transaction: DonationTransaction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
        }
    }

    fun getTotalDonation(id: Int) : LiveData<Double> {
        return repository.getTotalDonation(id)
    }

    fun getTransactionById(id: Int) : LiveData<List<DonationTransaction>> {
        return repository.getTransactionById(id)
    }
}