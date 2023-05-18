package com.example.donation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val userType: String = "user",
    val profilePic: ByteArray = ByteArray(0),
    var name: String,
    var username: String,
    var email: String,
    val password: String,
) {
}