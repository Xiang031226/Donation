package com.example.donation.model

import androidx.annotation.DrawableRes

// volunteer class
class Volunteer(
    @DrawableRes val eventImage: Int,
    val eventTitle: String,
    val eventDate: String,
    val eventTime: String,
    val eventLocation: String,
    val availableVolunteerRole: List<VolunteerRole>,
) {
}

//my volunteer role description, each event have more than one role
//each role have more than one job scope
//each job scope have more than one skill can be acquired
data class VolunteerRole(
    val role: String,
    val jobScopes: List<String>,
    val skills: List<String>,
    val qtyNeeded : Int = 0
) {

}
