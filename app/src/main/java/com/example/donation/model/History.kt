package com.example.donation.model

data class History(
    val donation_title: String,
    val donation_date: String,
    val donation_time: String,
    val amount_donated: Double,
    val description: Details,
    var isExpandable : Boolean = false
)

data class Details(
    val category : String,
    val payment_method : String,
    val donation_type : String,
)