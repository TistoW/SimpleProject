package com.tisto.simpleapp.core.di

import androidx.room.Room
import com.tisto.simpleapp.core.source.local.AppDatabase
import com.tisto.simpleapp.core.source.local.LocalDataSource
import com.tisto.simpleapp.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                Constants.DB_NAME
        ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    single { LocalDataSource(get()) }

}