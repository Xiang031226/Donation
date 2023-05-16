package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM users WHERE name = :name")
    fun getByName(name: String): User

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
