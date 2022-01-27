package com.zuluft.login.domain.use_cases

import com.zuluft.api.usecases.BaseUseCase
import com.zuluft.login.domain.models.LoginDataModel
import io.reactivex.Observable

class LoginAndSaveUserUseCase(
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : BaseUseCase<LoginDataModel, Observable<Boolean>> {
    override fun invoke(arg: LoginDataModel?): Observable<Boolean> {
        return loginUseCase(arg!!)
            .flatMap {
                saveUserUseCase(it)
            }
    }
}