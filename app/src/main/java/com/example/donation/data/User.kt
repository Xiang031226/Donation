package com.example.donation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val userType: String = "user",
    val profilePic: ByteArray = ByteArray(0),
    val name: String,
    val username: String,
    val email: String,
    val password: String,
) {
}