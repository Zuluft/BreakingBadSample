package com.zuluft.login.domain.use_cases

import com.zuluft.api.usecases.BaseUseCaseWithRepo
import com.zuluft.login.domain.models.UserFaceModel
import com.zuluft.login.domain.repos.LoginRepository
import io.reactivex.Observable

class SaveUserUseCase(repository: LoginRepository) :
    BaseUseCaseWithRepo<LoginRepository, UserFaceModel, Observable<Boolean>>(repository) {
    override fun invoke(arg: UserFaceModel?): Observable<Boolean> {
        return repository.saveUser(arg!!)
    }
}