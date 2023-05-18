package com.example.donation

import androidx.lifecycle.LiveData
import com.example.donation.dao.CardPaymentDao
import com.example.donation.data.CardPayment

class CardRepository(private val cardPaymentDao: CardPaymentDao) {

    val readAllData: LiveData<List<CardPayment>> = cardPaymentDao.readAllData()

    suspend fun addCard(card: CardPayment) {
        cardPaymentDao.addCard(card)
    }

    suspend fun deleteCard(card: CardPayment) {
        cardPaymentDao.deleteCard(card)
    }

    fun getCardById(id: Int): LiveData<CardPayment> {
        return cardPaymentDao.getCardById(id)
    }
}