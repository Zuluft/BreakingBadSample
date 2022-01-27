package com.zuluft.restore_password.di

import com.zuluft.restore_password.presentation.RestorePasswordViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RESTORE_PASSWORD_MODULE = module {
    viewModel {
        RestorePasswordViewModel()
    }
}