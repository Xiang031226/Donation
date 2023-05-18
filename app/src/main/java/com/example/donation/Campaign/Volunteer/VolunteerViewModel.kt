package com.example.donation.Campaign.Volunteer

import android.app.Application
import androidx.lifecycle.*
import com.example.donation.dao.VolunteerDbDao
import com.example.donation.database.AppDatabase
import com.example.donation.model.Volunteer
import com.example.donation.model.VolunteerDb
import com.example.donation.repositories.VolunteerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VolunteerViewModel(application: Application) : AndroidViewModel(application) {

    //Adapter
    val selectedEvent = MutableLiveData<Volunteer>()

    //For database
    val readAllData : LiveData<List<VolunteerDb>>
    private val repository: VolunteerRepository

    init {
        val volunteerDbDao = AppDatabase.getDatabase(application).volunteerDbDao()
        repository = VolunteerRepository(volunteerDbDao)
        readAllData = repository.readAllData
    }

    fun addVolunteer(volunteer: VolunteerDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVolunteer(volunteer)
        }
    }

    fun update(volunteer: VolunteerDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVolunteer(volunteer)
        }
    }
}