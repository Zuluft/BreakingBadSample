package com.zuluft.login.domain.providers.global

import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.models.UserFaceModel
import io.reactivex.Observable

interface LoginGlobalDataProvider {
    fun login(arg: LoginDataModel): Observable<UserFaceModel>
}