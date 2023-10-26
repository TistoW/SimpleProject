package com.tisto.simpleapp.util

import at.favre.lib.crypto.bcrypt.BCrypt
import com.inyongtisto.myhelper.extension.currentTime
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.randomInt
import com.tisto.simpleapp.R
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.regex.Pattern


/**
 * Created by Tisto on 27/10/2023.
 */

fun String.isEmailValid(): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})([.]{1})(.{1,})"
    val pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.encryptPassword(): String {
    return BCrypt.withDefaults().hashToString(12, this.toCharArray())
}

fun verifyPassword(password: String, bcryptHashString: String): Boolean {
    logs("password:$password - $bcryptHashString")
    return BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString).verified
}

fun writeLog(message: String) {
    val logPath = Prefs.pathLog
    val fileLog = File(logPath)
    if (!fileLog.exists()) {
        fileLog.parentFile?.mkdirs()
        val writer1 = BufferedWriter(FileWriter(fileLog))
        writer1.write("${currentTime()}-$message")
        writer1.close()
    }
}

fun randomColor(): Int {
    val list = listOf(
        R.color.color1,
        R.color.color2,
        R.color.color3,
        R.color.color4,
        R.color.color5,
        R.color.color6,
        R.color.color7,
        R.color.color8,
        R.color.color9,
        R.color.color10,
    )
    return list[randomInt(1, 10)]
}

fun String?.getSingleInitial(): String {
    try {
        if (this.isNullOrEmpty()) return ""
        val array = this.split(" ")
        if (array.isEmpty()) return this
        val inisial = array[0].substring(0, 1)
        return inisial.uppercase()
    } catch (e: Exception) {
        return "N"
    }
}