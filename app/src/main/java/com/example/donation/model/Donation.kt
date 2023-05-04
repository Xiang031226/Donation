package com.example.donation.model

import androidx.annotation.DrawableRes

data class Donation(
  val id: Int,
  @DrawableRes val imageResourceId: Int,
  val title: String,
  val category: String
)