package com.zuluft.login.domain.use_cases

import com.zuluft.api.usecases.BaseUseCaseWithRepo
import com.zuluft.login.domain.models.LoginDataModel
import com.zuluft.login.domain.models.UserFaceModel
import com.zuluft.login.domain.repos.LoginRepository
import io.reactivex.Observable

class LoginUseCase(repository: LoginRepository) :
    BaseUseCaseWithRepo<LoginRepository, LoginDataModel, Observable<UserFaceModel>>(repository) {
    override fun invoke(arg: LoginDataModel?): Observable<UserFaceModel> {
        return repository.login(arg!!)
    }
}