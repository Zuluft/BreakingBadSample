package com.zuluft.api.api.internal

internal interface UiLayer<T> {
    fun prepareView()
    fun reflectState(state: T)
}