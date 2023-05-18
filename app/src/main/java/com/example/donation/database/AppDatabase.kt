package com.example.donation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.donation.dao.CardPaymentDao
import com.example.donation.dao.SubscriptionDao
import com.example.donation.dao.TransactionDao
import com.example.donation.dao.UserDao
import com.example.donation.data.CardPayment
import com.example.donation.data.DonationTransaction
import com.example.donation.data.Subscription
import com.example.donation.data.User

@Database(entities = [User::class,
                     CardPayment::class,
                     DonationTransaction::class,
                     Subscription::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cardPaymentDao(): CardPaymentDao
    abstract fun TransactionDao() : TransactionDao
    abstract fun SubscriptionDao() : SubscriptionDao

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