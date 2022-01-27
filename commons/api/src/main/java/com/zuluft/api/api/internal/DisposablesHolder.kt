package com.zuluft.api.api.internal

import io.reactivex.disposables.Disposable

internal interface DisposablesHolder {
    fun registerDisposables(vararg disposables: Disposable)
    fun clearDisposables()
}