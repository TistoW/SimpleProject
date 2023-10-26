package com.tisto.simpleapp.util

import android.app.Application
import com.tisto.simpleapp.core.di.appModule
import com.tisto.simpleapp.core.di.repositoryModule
import com.tisto.simpleapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

//        Kotpref.init(this)
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}