@file:Suppress("unused")

package com.zuluft.koin_api.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class KoinApp :
    Application() {

    override fun onCreate() {
        super.onCreate()
        createKoinGraph()
    }

    private fun createKoinGraph() {
        startKoin {
            androidContext(applicationContext)
            modules(provideModules())
        }
    }

    fun resetKoinGraph() {
        stopKoin()
        createKoinGraph()
    }

    abstract fun provideModules(): List<Module>

}

