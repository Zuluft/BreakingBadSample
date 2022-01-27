package com.zuluft.login.domain.providers.local

import com.zuluft.login.domain.models.UserFaceModel
import io.reactivex.Observable

interface LoginLocalDataProvider {
    fun saveUser(arg: UserFaceModel): Observable<Boolean>
}