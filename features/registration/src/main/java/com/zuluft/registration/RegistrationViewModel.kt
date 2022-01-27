package com.zuluft.registration

import com.zuluft.api.viewModels.BaseViewModel

class RegistrationViewModel : BaseViewModel<RegistrationViewState>() {
    override fun getInitialState(): RegistrationViewState {
        return RegistrationViewState()
    }
}