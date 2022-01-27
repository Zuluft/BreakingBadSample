package com.zuluft.login.domain.providers.global.services

import com.zuluft.shared.models.login.LoginRequestModel
import com.zuluft.shared.models.login.LoginResponseModel
import io.reactivex.Observable
import retrofit2.http.POST

interface LoginServices {
    @POST("login")
    fun login(loginRequestModel: LoginRequestModel): Observable<LoginResponseModel>
}