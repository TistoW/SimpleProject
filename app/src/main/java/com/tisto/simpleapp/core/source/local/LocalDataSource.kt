package com.tisto.simpleapp.core.source.local


class LocalDataSource(db: AppDatabase) {

    private val dao = db.userDao()
    fun login() = dao.getAll()
    fun register() = dao.getAll()

}