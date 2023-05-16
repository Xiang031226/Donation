package com.example.donation

import androidx.lifecycle.LiveData
import com.example.donation.dao.UserDao
import com.example.donation.data.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}