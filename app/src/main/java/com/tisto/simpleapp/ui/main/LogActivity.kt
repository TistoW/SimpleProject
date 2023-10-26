package com.tisto.simpleapp.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tisto.simpleapp.databinding.ActivityLogBinding
import com.tisto.simpleapp.util.Prefs
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@SuppressLint("CustomSplashScreen")
class LogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = File(Prefs.pathLog)
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file))
            val fileContent = reader.readText()
            reader.close()

            binding.tvLog.text = fileContent
        } else {
            binding.tvLog.text = "Tidak ada log"
        }


    }
}