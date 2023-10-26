package com.tisto.simpleapp.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tisto.simpleapp.core.source.local.dao.UserDao
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import com.tisto.simpleapp.util.Constants

@Database(
    entities = [
        UserEntity::class,
    ], version = Constants.DB_VERSION, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}