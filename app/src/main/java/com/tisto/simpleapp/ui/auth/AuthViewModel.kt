package com.tisto.simpleapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tisto.simpleapp.core.repo.AuthRepository
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    fun getByEmail(search: String) = repo.getByEmail(search).asLiveData()
    fun get(search: String? = null) = repo.getLocal(search).asLiveData()

    fun create(data: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(data)
    }

    fun delete(data: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(data)
    }

    fun update(data: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(data)
    }

    fun clear() = viewModelScope.launch(Dispatchers.IO) {
        repo.clearAll()
    }
}