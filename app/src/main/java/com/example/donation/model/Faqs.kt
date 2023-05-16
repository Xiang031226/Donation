package com.example.donation.model

data class Faqs(
    val question : String,
    val answer : String,
    var isExpanded : Boolean = false
) {
}