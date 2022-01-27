package com.zuluft.mocks.di

import com.zuluft.mocks.reader.RawJsonReader
import com.zuluft.mocks.reader.RawJsonReaderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val MOCKS_MODULE = module {
    single {
        RawJsonReaderImpl(androidContext().resources)
    } bind RawJsonReader::class
}