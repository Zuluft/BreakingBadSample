package com.zuluft.breakingbadapp.app

import com.zuluft.core.CORE_NETWORKING_MODULE
import com.zuluft.koin_api.app.KoinApp
import com.zuluft.mocks.di.MOCKS_MODULE
import org.koin.core.module.Module

class App : KoinApp() {
    override fun provideModules(): List<Module> {
        return listOf(CORE_NETWORKING_MODULE, MOCKS_MODULE)
    }
}