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
