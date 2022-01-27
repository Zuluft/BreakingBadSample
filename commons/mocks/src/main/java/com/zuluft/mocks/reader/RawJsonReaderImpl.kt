package com.zuluft.mocks.reader

import android.content.res.Resources
import androidx.annotation.RawRes

class RawJsonReaderImpl(private val resources: Resources) : RawJsonReader {
    override fun readRawRes(@RawRes resId: Int): String {
        return resources.openRawResource(resId).run {
            val bytes = readBytes()
            close()
            String(bytes)
        }
    }
}