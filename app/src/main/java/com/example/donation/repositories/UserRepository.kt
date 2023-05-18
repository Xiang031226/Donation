package com.example.donation.repositories

import androidx.lifecycle.LiveData
import com.example.donation.dao.UserDao
import com.example.donation.model.User

class UserRepository(private val userDao: UserDao) {
    //Insert
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    //Read
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    //Update
    suspend fun update(user: User) {
        userDao.update(user)
    }

    //Delete
    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }

    fun getByUserameOrEmail(username: String, email: String): LiveData<User> {
        return userDao.getByUserameOrEmail(username, email)
    }

    fun getByName(name: String): User {
        return userDao.getByName(name)
    }
}