package com.zuluft.intro.presentation

import com.zuluft.api.models.DisposableValue

data class IntroViewState(
    val goToLogin: DisposableValue<Boolean>? = null
)