package com.zuluft.intro.di

import com.zuluft.controllers.LogoController
import com.zuluft.controllers.LogoControllerImpl
import com.zuluft.intro.presentation.IntroViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val INTRO_MODULE = module(override = true) {
    viewModel {
        IntroViewModel()
    }

    single {
        LogoControllerImpl()
    } bind LogoController::class
}