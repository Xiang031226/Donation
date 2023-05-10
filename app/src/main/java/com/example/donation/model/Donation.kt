package com.example.donation.model

import androidx.annotation.DrawableRes

//my animal donation data class
data class Donation(
  @DrawableRes val imageResourceId: Int,
  val title: String,
  val category: String,
  val animalDescription: AnimalDescription
)

//my animal description data class
data class AnimalDescription(
  val title: String,
  @DrawableRes val titleImage: Int,
  @DrawableRes val image2: Int,
  @DrawableRes val image3: Int,
  val extinctionTitle: String,
  val threatTitle: String,
  val mustDoTitle: String,
  val awareness: String,
  val threatMsg: String,
  val mustDoMsg: String,
  val supportMsg: String,
) {
}