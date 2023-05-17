package com.example.donation

import android.app.Application
import android.content.Context
import androidx.lifecycle.*

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var sharedPrefs = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    private val isLoggedInKEY = "isLoggedIn"
    private val userTypeKey = "userType"
    private val emailKey = "userEmail"
    private val usernameKey = "userName"

    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: LiveData<Boolean>
    get() = _isLoggedIn

    private val _userType = MutableLiveData("user")
    val userType: LiveData<String>
    get() = _userType

    private val _userEmail = MutableLiveData("")
    val userEmail: LiveData<String>
    get() = _userEmail

    private val _userName = MutableLiveData("")
    val userName: LiveData<String>
    get() = _userName

    init {
        _isLoggedIn.value = sharedPrefs.getBoolean(isLoggedInKEY, false)
        _userType.value = sharedPrefs.getString(userTypeKey, "user") ?: "user"
        _userEmail.value = sharedPrefs.getString(emailKey, "")
        _userName.value = sharedPrefs.getString(usernameKey, "")
    }

    fun saveLoginState() {
        sharedPrefs.edit().putBoolean(isLoggedInKEY, _isLoggedIn.value ?: false).apply()
        sharedPrefs.edit().putString(userTypeKey, _userType.value ?: "user").apply()
        sharedPrefs.edit().putString(emailKey, _userEmail.value ?: "").apply()
        sharedPrefs.edit().putString(usernameKey, _userName.value ?: "").apply()
    }

    fun setLoggedIn(isLoggedIn : Boolean) {
        _isLoggedIn.value = isLoggedIn
    }

    fun setUserType(userType: String) {
        _userType.value = userType
    }

    fun setUserEmail(userEmail : String) {
        _userEmail.value = userEmail
    }

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    fun logout() {
        setLoggedIn(false)
        setUserType("user")
        saveLoginState()
    }
}