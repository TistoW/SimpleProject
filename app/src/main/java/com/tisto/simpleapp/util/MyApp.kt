package com.tisto.simpleapp.util

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.inyongtisto.myhelper.extension.currentTime
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toModel
import com.tisto.simpleapp.core.di.appModule
import com.tisto.simpleapp.core.di.repositoryModule
import com.tisto.simpleapp.core.di.viewModelModule
import com.tisto.simpleapp.core.source.model.Config
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)

        val path = "$filesDir/config/"
        val configPath = "$path/config.txt"
        val logPath = "$path/logs.txt"
        Prefs.pathConfig = configPath
        Prefs.pathLog = logPath
        val textToWrite = "{\"dbName\":\"DB\",\"version\":1}"
        val fileConfig: Config
        try {
            val file = File(configPath)
            if (!file.exists()) {
                file.parentFile?.mkdirs()
                val writer = BufferedWriter(FileWriter(file))
                writer.write(textToWrite)
                writer.close()

                writeLog("create-database")
            }

            val reader = BufferedReader(FileReader(file))
            val fileContent = reader.readText()
            reader.close()

            fileConfig = fileContent.toModel(Config::class.java) ?: Config(
                Constants.DB_NAME,
                Constants.DB_VERSION
            )
            logs("filePath:$configPath")
            Prefs.setFileConfig(fileConfig)
        } catch (e: IOException) {
            println("An error occurred: ${e.message}")
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}