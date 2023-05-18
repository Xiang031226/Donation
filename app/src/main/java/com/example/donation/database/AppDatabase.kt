package com.example.donation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.donation.converter.Converters
import com.example.donation.dao.UserDao
import com.example.donation.dao.VolunteerDbDao
import com.example.donation.model.User
import com.example.donation.model.VolunteerDb

@Database(entities = [User::class, VolunteerDb::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun volunteerDbDao(): VolunteerDbDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "WildcareDB"
                )
                    .fallbackToDestructiveMigration() // Enable destructive migration
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}