package com.tisto.simpleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inyongtisto.myhelper.extension.getRandomName
import com.inyongtisto.myhelper.extension.getString
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toIntSafety
import com.inyongtisto.myhelper.extension.toastSuccess
import com.tisto.simpleapp.R
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import com.tisto.simpleapp.databinding.ActivityRegisterBinding
import com.tisto.simpleapp.util.encryptPassword
import com.tisto.simpleapp.util.isEmailValid
import com.tisto.simpleapp.util.writeLog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainButton()
    }

    private fun mainButton() {
        binding.apply {
            btnRegister.setOnClickListener {
                checkEmail()
            }

            btnDummy.setOnClickListener {
                edtName.setText(getRandomName(true))
                edtEmail.setText("${edtName.getString()}@gmail.com")
                edtAge.setText("20")
                edtPassword.setText("Password123")
            }
        }
    }

    private fun checkEmail() {
        viewModel.getByEmail(binding.edtEmail.getString()).observe(this) {
            writeLog("select-user-by-email ${binding.edtEmail.getString()}")
            if (it == null) {
                register()
            } else {
                binding.edtEmail.error = getString(R.string.email_telah_terdaftar)
            }
        }
    }

    private fun register() {
        if (isValid()) {
            val name = binding.edtName.getString()
            val email = binding.edtEmail.getString()
            val age = binding.edtAge.getString()
            val password = binding.edtPassword.getString()
            val body = UserEntity(
                name = name,
                email = email,
                age = age.toIntSafety(),
                password = password.encryptPassword(),
            )
            writeLog("create-user-name:$name,email:$email,age:$age,password:$password")
            viewModel.create(body)
            toastSuccess(getString(R.string.register_berhasil_silahkan_login))
            pushActivity(LoginActivity::class.java)
        }
    }

    private fun isValid(): Boolean {
        binding.apply {
            val password = edtPassword.getString()

            if (edtName.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (!edtEmail.getString().isEmailValid()) {
                edtEmail.error = getString(R.string.email_tidak_valid)
                return false
            }
            if (edtAge.isEmpty()) return false
            if (edtAge.getString().toIntSafety() < 18) {
                edtAge.error = getString(R.string.umur_tidak_boleh_kurang_dari_18_tahun)
                return false
            }
            if (edtPassword.isEmpty()) return false
            if (password.length < 6) {
                edtPassword.error = getString(R.string.password_minimal_6_karakter)
                return false
            }
            if (!password.any { it.isUpperCase() }) {
                edtPassword.error = getString(R.string.password_minimal_terdapat_1_huruf_kapital)
                return false
            }
            if (!password.any { it.isDigit() }) {
                edtPassword.error = getString(R.string.password_harus_gabungan_huruf_dan_angka)
                return false
            }
        }
        return true
    }
}