package com.zuluft.restore_password.presentation

import com.zuluft.api.viewModels.BaseViewModel

class RestorePasswordViewModel : BaseViewModel<RestorePasswordViewState>() {
    override fun getInitialState(): RestorePasswordViewState {
        return RestorePasswordViewState()
    }
}