package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.data.CardPayment

@Dao
interface CardPaymentDao {
    @Query("SELECT * FROM cardPayment")
    fun readAllData(): LiveData<List<CardPayment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: CardPayment)

    @Delete
    suspend fun deleteCard(card: CardPayment)

    @Query("SELECT * FROM cardPayment WHERE id = :id")
    fun getCardById(id: Int): LiveData<CardPayment>
}