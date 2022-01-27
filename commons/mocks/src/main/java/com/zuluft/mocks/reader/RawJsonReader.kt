package com.zuluft.mocks.reader

import androidx.annotation.RawRes

interface RawJsonReader {
    fun readRawRes(@RawRes resId: Int): String
}