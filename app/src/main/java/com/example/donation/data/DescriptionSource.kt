package com.example.donation.data

import android.content.Context
import com.example.donation.R
import com.example.donation.model.Description

class DescriptionSource {
    //passing context so I can use it to retrieve my string contents
    //otherwise my string will be int value, hmm, why so troublesome lol
    fun animalDescriptionList(context: Context) : List<Description> {
        return listOf(
            Description("Save Our Tiger",
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
            Description(
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
            Description(
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
            Description(
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
    }
}