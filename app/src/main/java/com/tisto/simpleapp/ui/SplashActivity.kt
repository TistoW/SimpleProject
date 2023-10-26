package com.tisto.simpleapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.pushActivity
import com.tisto.simpleapp.databinding.ActivitySplashBinding
import com.tisto.simpleapp.ui.auth.LoginActivity
import com.tisto.simpleapp.ui.main.MainActivity
import com.tisto.simpleapp.util.Prefs

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!Prefs.isLogin) {
            pushActivity(LoginActivity::class.java)
        } else {
            pushActivity(MainActivity::class.java)
        }
    }
}