package com.zuluft.login.domain.providers.global.services

import com.google.gson.Gson
import com.zuluft.api.extensions.just
import com.zuluft.login.R
import com.zuluft.mocks.reader.RawJsonReader
import com.zuluft.shared.models.login.LoginRequestModel
import com.zuluft.shared.models.login.LoginResponseModel
import io.reactivex.Observable
import org.json.JSONArray
import java.util.concurrent.TimeUnit

class LoginServicesImplFake(
    private val rawJsonReader: RawJsonReader,
    private val gson: Gson
) : LoginServices {
    override fun login(loginRequestModel: LoginRequestModel): Observable<LoginResponseModel> {
        return Observable.timer(1, TimeUnit.MILLISECONDS)
            .flatMap {
                val jsonArr =
                    JSONArray(rawJsonReader.readRawRes(R.raw.users))
                for (i in 0 until jsonArr.length()) {
                    val jsonObj = jsonArr.getJSONObject(i)
                    if (jsonObj.getString("username") == loginRequestModel.username
                        && jsonObj.getString("password") == loginRequestModel.password
                    ) {
                        return@flatMap gson.fromJson(
                            jsonObj.toString(),
                            LoginResponseModel::class.java
                        ).just()
                    }
                }
                throw RuntimeException("Invalid username or password")
            }
    }
}
