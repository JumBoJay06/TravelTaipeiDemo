package com.jumbojay.traveltaipeidemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jumbojay.traveltaipeidemo.data.Attractions
import com.jumbojay.traveltaipeidemo.database.dao.AttractionsDao
import java.util.concurrent.Executors

@Database(
    entities = [Attractions::class],
    version = 0
)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun attractionsDao(): AttractionsDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getDatabase(context: Context): MyRoomDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoomDatabase::class.java,
                        "my_database"
                    )
                        .build()
                    INSTANCE = instance
                    instance
                }
        }
    }

    fun deleteAll() {
        Executors.newCachedThreadPool().execute {
            clearAllTables()
        }
    }
}