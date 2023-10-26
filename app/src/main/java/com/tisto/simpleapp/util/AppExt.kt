package com.tisto.simpleapp.util

import at.favre.lib.crypto.bcrypt.BCrypt
import com.inyongtisto.myhelper.extension.logs
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