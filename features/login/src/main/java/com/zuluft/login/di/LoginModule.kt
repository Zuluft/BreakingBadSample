package com.zuluft.login.di

import com.zuluft.login.domain.providers.global.LoginGlobalDataProvider
import com.zuluft.login.domain.providers.global.LoginGlobalDataProviderImpl
import com.zuluft.login.domain.providers.global.services.LoginServices
import com.zuluft.login.domain.providers.global.services.LoginServicesImplFake
import com.zuluft.login.domain.providers.local.LoginLocalDataProvider
import com.zuluft.login.domain.providers.local.LoginLocalDataProviderImpl
import com.zuluft.login.domain.repos.LoginRepository
import com.zuluft.login.domain.repos.LoginRepositoryImpl
import com.zuluft.login.domain.use_cases.LoginAndSaveUserUseCase
import com.zuluft.login.domain.use_cases.LoginUseCase
import com.zuluft.login.domain.use_cases.SaveUserUseCase
import com.zuluft.login.presentation.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val LOGIN_MODULE = module(override = true) {

    single {
        LoginServicesImplFake(get(), get())
    } bind LoginServices::class

    single {
        LoginLocalDataProviderImpl()
    } bind LoginLocalDataProvider::class

    single {
        LoginGlobalDataProviderImpl(get())
    } bind LoginGlobalDataProvider::class

    single {
        LoginRepositoryImpl(get(), get())
    } bind LoginRepository::class

    factory {
        LoginUseCase(get())
    }

    factory {
        SaveUserUseCase(get())
    }

    factory {
        LoginAndSaveUserUseCase(get(), get())
    }

    viewModel {
        LoginViewModel(get())
    }


}