package com.example.donation.model

import androidx.annotation.DrawableRes

//my animal description data class
data class Description(
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
