package com.zuluft.login.domain.repos

import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.models.UserFaceModel
import com.zuluft.login.domain.providers.global.LoginGlobalDataProvider
import com.zuluft.login.domain.providers.local.LoginLocalDataProvider
import io.reactivex.Observable

class LoginRepositoryImpl(
    private val loginGlobalDataProvider: LoginGlobalDataProvider,
    private val loginLocalDataProvider: LoginLocalDataProvider
) : LoginRepository {
    override fun login(arg: LoginDataModel): Observable<UserFaceModel> {
        return loginGlobalDataProvider.login(arg)
    }

    override fun saveUser(arg: UserFaceModel): Observable<Boolean> {
        return loginLocalDataProvider.saveUser(arg)
    }
}