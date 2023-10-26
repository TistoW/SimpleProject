package com.tisto.simpleapp.core.di

import com.tisto.simpleapp.core.repo.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
}
