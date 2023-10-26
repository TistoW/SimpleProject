package com.tisto.simpleapp.util

import android.annotation.SuppressLint
import com.chibatching.kotpref.KotprefModel
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import com.tisto.simpleapp.core.source.model.Config

@SuppressLint("SdCardPath")
object Prefs : KotprefModel() {
    var isLogin by booleanPref(false)
    var pathConfig by stringPref("/data/user/0/com.tisto.simpleapp/files/config/config.txt")
    var pathLog by stringPref("/data/user/0/com.tisto.simpleapp/files/config/log.txt")
    private var user by nullableStringPref()
    private var fileConfig by nullableStringPref()

    fun setUser(data: UserEntity?) {
        user = data?.toJson()
    }

    fun getUser(): UserEntity? {
        if (user.isNullOrEmpty()) return null
        return user.toModel(UserEntity::class.java)
    }

    fun setFileConfig(data: Config?) {
        fileConfig = data?.toJson()
    }

    fun getFileConfig(): Config {
        return fileConfig.toModel(Config::class.java) ?: Config(
            Constants.DB_NAME,
            Constants.DB_VERSION
        )
    }
}
