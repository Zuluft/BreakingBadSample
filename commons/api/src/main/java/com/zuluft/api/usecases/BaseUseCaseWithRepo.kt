@file:Suppress("unused")

package com.zuluft.api.usecases

abstract class BaseUseCaseWithRepo<REPOSITORY, ARG_TYPE, RETURN_TYPE>
constructor(protected val repository: REPOSITORY) :
    BaseUseCase<ARG_TYPE, RETURN_TYPE>