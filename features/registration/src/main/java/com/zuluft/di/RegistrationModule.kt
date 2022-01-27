package com.zuluft.di

import com.zuluft.registration.RegistrationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val REGISTRATION_MODULE = module(override = true) {
    viewModel {
        RegistrationViewModel()
    }
}