package com.zuluft.login.domain.repos

import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.models.UserFaceModel
import io.reactivex.Observable

interface LoginRepository {
    fun login(arg: LoginDataModel): Observable<UserFaceModel>
    fun saveUser(arg: UserFaceModel): Observable<Boolean>
}