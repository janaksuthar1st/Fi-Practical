package com.fi.practicaljanak.di

import com.fi.practicaljanak.viewmodels.HomeScreenViewModel
import com.fi.practicaljanak.viewmodels.SplashViewModel
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val uiModules = arrayOf(
    module {
        viewModel { SplashViewModel(get()) }
        viewModel { HomeScreenViewModel(get(),get()) }
    }
)