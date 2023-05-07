package com.example.donation.data

import com.example.donation.R
import com.example.donation.model.Donation

object DonationSource {
    val donation: List<Donation> = listOf(
        Donation(
            R.drawable.image1,
            "Save Our Tigers",
            "Wild Life"
        ),Donation(
            R.drawable.image2,
            "Save Our Elephants",
            "Wild Life"
        ),Donation(
            R.drawable.image3,
            "Save Our Sea Turtles",
            "Marine"
        ),Donation(
            R.drawable.image4,
            "Save Our Orang Utans",
            "Wild Life"
        )
    )
}