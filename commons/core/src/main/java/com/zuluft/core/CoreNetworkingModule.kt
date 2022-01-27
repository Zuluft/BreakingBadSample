package com.zuluft.core

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val CORE_NETWORKING_MODULE = module {

    single {
        GsonBuilder().create()
    }
}