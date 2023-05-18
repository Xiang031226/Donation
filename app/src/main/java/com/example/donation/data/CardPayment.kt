package com.example.donation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardPayment")
class CardPayment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cardNumber: String,
    val expiredDate: String,
    val cvv: Int,
    val userId: Int = 0
) {
}