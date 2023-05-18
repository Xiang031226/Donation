package com.example.donation.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.donation.model.User
import java.time.temporal.TemporalAmount

@Entity(tableName = "subscription", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])
data class Subscription(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val userId : Int,
    val donationGoal: String,
    val amount: Double,
    val paymentMethod: String,
    val progress: Int,
) {
}