package com.zuluft.intro.presentation

import com.zuluft.api.models.DisposableValue
import com.zuluft.api.viewModels.BaseViewModel

class IntroViewModel : BaseViewModel<IntroViewState>() {

    override fun getInitialState(): IntroViewState {
        return IntroViewState()
    }

    fun introSceneFinishedIntent() {
        setState(
            constructState {
                copy(goToLogin = DisposableValue(true))
            })
    }
}