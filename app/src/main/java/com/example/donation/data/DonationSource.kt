package com.example.donation.data

import android.content.Context
import com.example.donation.R
import com.example.donation.model.AnimalDescription
import com.example.donation.model.Donation

class DonationSource(context: Context) {

    private val animalDescription = listOf(
        AnimalDescription("Save Our Tiger",
            R.drawable.image1,
            R.drawable.image1,
            R.drawable.image1,
            context.getString(R.string.tiger_extinctionTitle),
            context.getString(R.string.tiger_threatTitle),
            context.getString(R.string.tiger_MustDoTitle),
            context.getString(R.string.tiger_awareness),
            context.getString(R.string.tiger_threatMsg),
            context.getString(R.string.tiger_MustDoMsg),
            context.getString(R.string.tiger_supportMsg)),
        AnimalDescription(
            "Save Our Borneon Elephants",
            R.drawable.image2,
            R.drawable.image2,
            R.drawable.image2,
            context.getString(R.string.elephant_extinctionTitle),
            context.getString(R.string.elephant_threatTitle),
            context.getString(R.string.elephant_MustDoTitle),
            context.getString(R.string.elephant_awareness),
            context.getString(R.string.elephant_threatMsg),
            context.getString(R.string.elephant_MustDoMsg),
            context.getString(R.string.elephant_supportMsg)
        ),
        AnimalDescription(
            "Save Our Sea Turtles",
            R.drawable.image3,
            R.drawable.image3,
            R.drawable.image3,
            context.getString(R.string.sea_turtle_extinctionTitle),
            context.getString(R.string.sea_turtle_threatTitle),
            context.getString(R.string.sea_turtle_MustDoTitle),
            context.getString(R.string.sea_turtle_awareness),
            context.getString(R.string.sea_turtle_threatMsg),
            context.getString(R.string.sea_turtle_MustDoMsg),
            context.getString(R.string.sea_turtle_supportMsg)
        ),
        AnimalDescription(
            "Save Our Orang Utans",
            R.drawable.image4,
            R.drawable.image4,
            R.drawable.image4,
            context.getString(R.string.orang_utan_extinctionTitle),
            context.getString(R.string.orang_utan_threatTitle),
            context.getString(R.string.orang_utan_MustDoTitle),
            context.getString(R.string.orang_utan_awareness),
            context.getString(R.string.orang_utan_threatMsg),
            context.getString(R.string.orang_utan_MustDoMsg),
            context.getString(R.string.orang_utan_supportMsg)),
    )

    fun loadAnimalDescriptionData() : List<Donation> {
        return listOf(
            Donation(
                R.drawable.sad_tiger,
                "Save Our Tigers",
                "Wild Life",
                animalDescription = animalDescription[0]
                ),
            Donation(
                R.drawable.Elephants,
                "Save Our Elephants",
                "Wild Life",
                animalDescription = animalDescription[1]
            ),Donation(
                R.drawable.orang_utans,
                "Save Our Sea Turtles",
                "Marine",
                animalDescription = animalDescription[2]
            ),Donation(
                R.drawable.sea_turtle,
                "Save Our Orang Utans",
                "Wild Life",
                animalDescription = animalDescription[3]
            )
        )
    }
}