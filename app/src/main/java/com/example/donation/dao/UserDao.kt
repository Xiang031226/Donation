package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserById(userId: Int): LiveData<User>

    @Query("SELECT * FROM users WHERE name = :name")
    fun getByName(name: String): LiveData<User>

    @Query("SELECT * FROM users WHERE username = :username OR email = :email")
    fun getByUserameOrEmail(username: String, email: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun update(user: User)

    @Query("UPDATE users SET name = :name, username = :username, email = :email WHERE name = :oldname AND email = :oldemail")
    suspend fun updateProfile(
        name: String,
        username: String,
        email: String,
        oldname: String,
        oldemail: String
    )

    @Delete
    suspend fun delete(user: User)
}
