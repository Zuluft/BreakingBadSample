package com.zuluft.api.models

abstract class BaseViewState(
    open val showLoader: DisposableValue<Boolean>? = null,
    open val showError: DisposableValue<Throwable>? = null
)