package com.example.donation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.model.User
import com.example.donation.database.AppDatabase
import com.example.donation.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    val currentUser = MutableLiveData<User>()
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun getUserById(id: Int): User {
        return repository.getUserById(id)
    }

    fun getByNameAndEmail(username: String, email: String): LiveData<User> {
        return repository.getByUserameOrEmail(username, email)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    fun delete(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }
}