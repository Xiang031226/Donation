package com.example.donation.model

import androidx.annotation.DrawableRes

//my animal donation data class
data class Donation(
  @DrawableRes val imageResourceId: Int,
  val title: String,
  val category: String
)