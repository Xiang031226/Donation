package com.example.donation.data

import android.util.Log
import com.example.donation.R
import com.example.donation.model.CreditCard

class CreditCardSource(private val cardList: MutableList<CreditCard> = mutableListOf()) {

    fun loadCreditCardList() : List<CreditCard> {
        return listOf(
            CreditCard(
                R.drawable.ic_card,
                "123",
                "123",
                123,
                R.drawable.ic_delete
            )
        )
    }

    fun addCard(data : CreditCard) {
        cardList.add(data)
    }
}