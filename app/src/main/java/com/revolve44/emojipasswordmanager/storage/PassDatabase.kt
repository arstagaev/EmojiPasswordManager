package com.revolve44.emojipasswordmanager.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revolve44.emojipasswordmanager.models.PairNameandPassword


@Database(
    entities = [PairNameandPassword::class],
    version = 1
)
abstract class PassDatabase : RoomDatabase(){
    abstract val passDao : PassDao

    companion object{
        @Volatile
        private var INSTANCE :PassDatabase? = null

        fun getInstance(context: Context) : PassDatabase {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PassDatabase::class.java,
                    "drowssap_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}