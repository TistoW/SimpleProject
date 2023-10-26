package com.tisto.simpleapp.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.getSalam
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.remove
import com.inyongtisto.myhelper.extension.showConfirmDialog
import com.tisto.simpleapp.databinding.ActivityMainBinding
import com.tisto.simpleapp.ui.auth.AuthViewModel
import com.tisto.simpleapp.ui.auth.LoginActivity
import com.tisto.simpleapp.ui.main.adapter.UserAdapter
import com.tisto.simpleapp.util.Prefs
import com.tisto.simpleapp.util.getSingleInitial
import com.tisto.simpleapp.util.randomColor
import com.tisto.simpleapp.util.writeLog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!Prefs.isLogin) {
            pushActivity(LoginActivity::class.java)
        }

        mainButton()
        setUi()
        observer()
    }

    private fun mainButton() {
        binding.apply {
            btnMore.setOnClickListener {
                popUpMenu(it, listOf("Log Database", "Logout")) { menu ->
                    when (menu) {
                        "Logout" -> logout()
                        "Log Database" -> intentActivity(LogActivity::class.java)
                    }
                }
            }
        }
    }

    private fun logout() {
        showConfirmDialog("Logout", "Apakah anda yakin ingin keluar dari akun ini?") {
            Prefs.isLogin = false
            Prefs.setUser(null)
            pushActivity(LoginActivity::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUi() {
        binding.apply {
            rvUser.adapter = adapter
            val user = Prefs.getUser()
            tvTitle.text = getSalam().remove("Selamat ") + ", ${user?.name}"
            tvPlaceholder.setBackgroundColor(getColor(user?.color ?: randomColor()))
            tvPlaceholder.text = user?.name.getSingleInitial()
        }
    }

    private fun observer() {
        viewModel.get().observe(this) {
            writeLog("get-all-user")
            adapter.submitList(it)
        }
    }
}