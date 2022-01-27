package com.zuluft.login.presentation

import com.zuluft.api.models.BaseViewState
import com.zuluft.api.models.DisposableValue

data class LoginViewState(
    val goToRegistrationScreen: DisposableValue<Boolean>? = null,
    val goToRestorePasswordScreen: DisposableValue<Boolean>? = null,
    val goToHomeScreen: DisposableValue<Boolean>? = null,
    override val showLoader: DisposableValue<Boolean>? = null,
    override val showError: DisposableValue<Throwable>? = null
) : BaseViewState(showLoader, showError)