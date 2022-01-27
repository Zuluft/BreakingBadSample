package com.zuluft.api.api.internal

import com.zuluft.api.viewModels.BaseViewModel

internal interface ViewModelProvider<S, V : BaseViewModel<S>> {
    fun provideViewModel(): V
}