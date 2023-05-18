package com.example.donation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.data.CardPayment
import com.example.donation.data.User
import com.example.donation.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<CardPayment>>
    private val repository: CardRepository

    init {
        val cardPaymentDao = AppDatabase.getDatabase(application).cardPaymentDao()
        repository = CardRepository(cardPaymentDao)
        readAllData = repository.readAllData
    }

    fun addCard(card: CardPayment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCard(card)
        }
    }

    fun deleteCard(card: CardPayment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(card)
        }
    }

    fun getCardById(id: Int): LiveData<CardPayment> {
        return repository.getCardById(id)
    }
}