package com.example.donation.model

import androidx.annotation.DrawableRes

data class Application(
    @DrawableRes val profilePicResId: Int,
    val username: String,
    val jobRole: String
)