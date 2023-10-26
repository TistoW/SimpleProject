package com.tisto.simpleapp.core.di

import androidx.room.Room
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toModel
import com.tisto.simpleapp.core.source.local.AppDatabase
import com.tisto.simpleapp.core.source.local.LocalDataSource
import com.tisto.simpleapp.core.source.model.Config
import com.tisto.simpleapp.util.Constants
import com.tisto.simpleapp.util.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

val appModule = module {

    single {
        val fileConfig = Prefs.getFileConfig()
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            fileConfig.dbName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { LocalDataSource(get()) }

}