package com.zuluft.login.presentation

import com.zuluft.api.extensions.async
import com.zuluft.api.models.DisposableValue
import com.zuluft.api.viewModels.BaseViewModel
import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.use_cases.LoginAndSaveUserUseCase

class LoginViewModel(
    private val loginAndSaveUserUseCase: LoginAndSaveUserUseCase
) : BaseViewModel<LoginViewState>() {

    override fun getInitialState(): LoginViewState {
        return LoginViewState()
    }

    fun onRegisterClickIntent() {
        setState(
            constructState {
                copy(goToRegistrationScreen = DisposableValue(true))
            }
        )
    }

    fun onRestorePasswordClickIntent() {
        setState(
            constructState {
                copy(goToRestorePasswordScreen = DisposableValue(true))
            }
        )
    }

    fun onLoginIntent(loginDataModel: LoginDataModel) {
        registerDisposables(
            loginAndSaveUserUseCase(loginDataModel)
                .map {
                    constructState {
                        copy(
                            goToHomeScreen = DisposableValue(true)
                        )
                    }
                }
                .onErrorReturn {
                    constructState {
                        copy(showError = DisposableValue(it))
                    }
                }
                .async()
                .startWith(constructState {
                    copy(showLoader = DisposableValue(true))
                })
                .subscribe(this::setState)
        )
    }
}