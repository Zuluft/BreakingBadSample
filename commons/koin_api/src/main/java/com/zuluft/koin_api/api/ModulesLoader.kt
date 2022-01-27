package com.zuluft.koin_api.api

import org.koin.core.module.Module

internal interface ModulesLoader {
    fun provideModulesToLoad(): List<Module>
    fun provideModulesToUnload(): List<Module>
}