package com.example.donation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.donation.data.User
import com.example.donation.database.UserDatabase
import com.github.mikephil.charting.utils.Utils.init
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val sharedPrefs = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    private val isLoggedInKEY = "isLoggedIn"
    private val userTypeKey = "userType"


    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: LiveData<Boolean>
    get() = _isLoggedIn

    private val _userType = MutableLiveData("user")
    val userType: LiveData<String>
    get() = _userType

    init {
        _isLoggedIn.value = sharedPrefs.getBoolean(isLoggedInKEY, false)
        _userType.value = sharedPrefs.getString(userTypeKey, "user") ?: "user"
    }

    fun saveLoginState() {
        sharedPrefs.edit().putBoolean(isLoggedInKEY, _isLoggedIn.value ?: false).apply()
        sharedPrefs.edit().putString(userTypeKey, _userType.value ?: "user").apply()
        Log.e("User type : ", _userType.value.toString())
    }

    fun setLoggedIn(isLoggedIn : Boolean) {
        _isLoggedIn.value = isLoggedIn
    }

    fun setUserType(userType: String) {
        _userType.value = userType
    }

    fun logout() {
        setLoggedIn(false)
        setUserType("user")
        saveLoginState()
    }
}