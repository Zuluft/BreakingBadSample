package com.zuluft.koin_test_api.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    abstract fun provideAppModules(): List<Module>


    @Before
    fun setupKoinGraph() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        startKoin {
            modules(provideAppModules())
        }
    }

    @After
    @Throws(Throwable::class)
    fun destroyKoinGraph() {
        stopKoin()
    }
}