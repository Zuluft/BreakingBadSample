package com.zuluft.login.domain.providers.local

import com.zuluft.api.extensions.just
import com.zuluft.login.domain.models.UserFaceModel
import io.reactivex.Observable

class LoginLocalDataProviderImpl() : LoginLocalDataProvider {
    override fun saveUser(arg: UserFaceModel): Observable<Boolean> {
        return true.just()
    }
}