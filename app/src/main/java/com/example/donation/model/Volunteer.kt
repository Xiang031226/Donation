package com.example.donation.model

import androidx.annotation.DrawableRes


class Volunteer(
    @DrawableRes val titleImage : Int,
    val title : String,
    val date : String,
    val time : String,
    val location : String
) {
}