package com.tisto.simpleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.intentActivity
import com.tisto.simpleapp.databinding.ActivityMainBinding
import com.tisto.simpleapp.ui.auth.LoginActivity
import com.tisto.simpleapp.ui.auth.RegisterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentActivity(LoginActivity::class.java)
    }
}