package com.tisto.simpleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.def
import com.inyongtisto.myhelper.extension.getString
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toastSuccess
import com.tisto.simpleapp.R
import com.tisto.simpleapp.databinding.ActivityLoginBinding
import com.tisto.simpleapp.util.verifyPassword
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    private fun mainButton() {
        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }

            btnRegister.setOnClickListener {
                intentActivity(RegisterActivity::class.java)
            }

            btnDummy.setOnClickListener {
                edtEmail.setText("admin@gmail.com")
                edtPassword.setText("Password123")
            }
        }
    }

    private fun login() {
        binding.apply {
            viewModel.getByEmail(edtEmail.getString()).observe(this@LoginActivity) {
                if (it != null) {
                    if (verifyPassword(edtPassword.text.toString(), it.password.def())) {
                        toastSuccess("Selamat datang ${it.name}")
                    } else {
                        edtPassword.error = getString(R.string.password_salah)
                    }
                } else {
                    edtEmail.error = getString(R.string.email_tidak_terdaftar)
                }
            }
        }
    }
}