package com.tisto.simpleapp.core.repo

import com.tisto.simpleapp.core.source.local.AppDatabase
import com.tisto.simpleapp.core.source.local.entity.UserEntity

class AuthRepository(private val db: AppDatabase) {
    private val local = db.userDao()
    fun update(data: UserEntity) = local.update(data)
    fun insert(data: UserEntity) = local.insert(data)
    fun delete(data: UserEntity) = local.delete(data)
    fun getLocal(search: String?) = local.getAll(search)
    fun getByEmail(email: String) = local.getByEmail(email)
    fun clearAll() = local.clearAll()
}