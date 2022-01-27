package com.zuluft.login.domain.providers.global

import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.models.UserFaceModel
import com.zuluft.login.domain.providers.global.services.LoginServices
import com.zuluft.shared.models.login.LoginRequestModel
import io.reactivex.Observable

class LoginGlobalDataProviderImpl(
    private val loginServices: LoginServices
) : LoginGlobalDataProvider {
    override fun login(arg: LoginDataModel): Observable<UserFaceModel> {
        return loginServices.login(
            arg.run {
                LoginRequestModel(username, password)
            }
        ).map {
            it.run {
                UserFaceModel(id, username, fullName, email)
            }
        }
    }
}