package com.example.donation.model

import androidx.annotation.DrawableRes


class Volunteer(
    @DrawableRes val eventImage: Int,
    val eventTitle: String,
    val eventDate: String,
    val eventTime: String,
    val eventLocation: String,
    val availableVolunteerRole: List<VolunteerRole>,
) {
}