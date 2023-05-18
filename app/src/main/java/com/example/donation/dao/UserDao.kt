package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM users WHERE name = :name")
    fun getByName(name: String): User

    @Query("SELECT * FROM users WHERE username = :username OR email = :email")
    fun getByUserameOrEmail(username: String, email: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}
