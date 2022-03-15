package com.fi.practicaljanak.di

import com.fi.practicaljanak.repository.network.FiNetworkRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { FiNetworkRepository(get()) }
}