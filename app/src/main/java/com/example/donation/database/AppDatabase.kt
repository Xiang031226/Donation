package com.example.donation.database

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.donation.dao.UserDao
//import com.example.donation.data.User
//
//@Database(entities = [User::class], version = 1)
//
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//
//    companion object {
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            if (instance == null) {
//                synchronized(AppDatabase::class) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "WildcareDb"
//                    ).build()
//                }
//            }
//            return instance as AppDatabase
//        }
//    }
//}