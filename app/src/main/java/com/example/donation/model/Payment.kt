package com.example.donation.model

data class Payment(
    val amount: Double,
    val paymentMethod: String = "Google Pay",
    val donationType : String
) {
}