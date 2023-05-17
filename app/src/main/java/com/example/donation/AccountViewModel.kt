package com.example.donation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.data.User
import com.example.donation.database.UserDatabase
import com.github.mikephil.charting.utils.Utils.init
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun adduser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}