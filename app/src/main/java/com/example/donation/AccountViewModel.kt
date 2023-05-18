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


    fun getByNameAndEmail(username: String, email: String): LiveData<User> {
        return repository.getByUserameOrEmail(username, email)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun getUserById(id : Int) : LiveData<User> {
        return repository.getUserById(id)
    }

    fun update(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    suspend fun updateProfile(
        name: String,
        username: String,
        email: String,
        oldname: String,
        oldemail: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateProfile(name, username, email, oldname, oldemail)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }
}