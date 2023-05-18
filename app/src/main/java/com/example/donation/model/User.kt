package com.example.donation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val userType: String = "user",
    val profilePic: ByteArray = ByteArray(0),
    var name: String,
    var username: String,
    var email: String,
    val password: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
}