package com.example.donation.model

import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes

data class CreditCard(
    @DrawableRes val cardImage: Int,
    val cardNumber : String,
    val expiredDate: String,
    val cvv : Int,
    @DrawableRes val deleteButton: Int
) {
}