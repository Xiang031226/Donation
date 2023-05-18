package com.example.donation.model

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.donation.R

@Entity(tableName = "volunteers")
data class VolunteerDb(
    val eventTitle: String,
    val eventDate: String,
    val eventTime: String,
    val eventLocation: String,
    val availableVolunteerTitle : String = "",
    val availableVolunteerQty : String = "",
    @DrawableRes val eventImage: Int = R.drawable.image2,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
}