package com.example.donation.repositories

import androidx.lifecycle.LiveData
import com.example.donation.dao.VolunteerDbDao
import com.example.donation.model.VolunteerDb

class VolunteerRepository(private val volunteerDao: VolunteerDbDao) {

    //Insert
    suspend fun addVolunteer(volunteer: VolunteerDb) {
        volunteerDao.addVolunteer(volunteer)
    }

    //Read
    val readAllData: LiveData<List<VolunteerDb>> = volunteerDao.readAllData()

    //Update
    suspend fun update(volunteer: VolunteerDb) {
        volunteerDao.update(volunteer)
    }

    //Delete
    suspend fun delete(volunteer: VolunteerDb) {
        volunteerDao.delete(volunteer)
    }
}