package com.example.donation.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.donation.model.VolunteerDb

@Dao
interface VolunteerDbDao {
    @Query("SELECT * FROM volunteers")
    fun readAllData(): LiveData<List<VolunteerDb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVolunteer(volunteer: VolunteerDb)

    @Update
    suspend fun update(volunteer: VolunteerDb)

    @Delete
    suspend fun delete(volunteer: VolunteerDb)
}