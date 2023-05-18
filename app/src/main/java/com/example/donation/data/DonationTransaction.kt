package com.example.donation.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "donationTransaction", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])
data class DonationTransaction(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val userId: Int,
    val donationTitle: String,
    val amount : Double,
    val date: String,
    val time: String,
    val donationType: String,
    val paymentMethod: String,
    val category: String,
    var isExpandable: Boolean = false
) {
}