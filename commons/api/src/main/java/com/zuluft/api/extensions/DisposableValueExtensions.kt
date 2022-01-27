@file:Suppress("unused")

package com.zuluft.api.extensions

import androidx.lifecycle.ViewModel
import com.zuluft.api.models.DisposableValue
import java.lang.RuntimeException

operator fun <T : Any> T.invoke(viewModel: ViewModel?) = if (viewModel != null) {
    DisposableValue(this)
} else {
    throw RuntimeException("viewModel is null")
}