package com.example.donation.data

import android.content.Context
import com.example.donation.model.Details
import com.example.donation.model.History

class HistorySource(context: Context) {

    private val details = arrayListOf(
        Details(
            "wildlife",
            "Touch NGo",
            "one-time"
        ),
        Details(
            "marine",
            "Google Pay",
            "one-time"
        )
    )

    fun loadHistoryData(): List<History> {
        return listOf(
            History(
                "Save Our Tiger",
                "31 March 2023",
                "5:53 pm",
                50.00,
                details[0]
            ),History(
                "Save Our Tiger",
                "31 March 2023",
                "5:53 pm",
                500.00,
                details[1]
            ),History(
                "Save Our Orang Utans",
                "31 March 2023",
                "5:53 pm",
                50.00,
                details[0]
            ),History(
                "Save Our Elephant",
                "30 April 2023",
                "5:53 pm",
                500.00,
                details[1]
            )
        )
    }
}