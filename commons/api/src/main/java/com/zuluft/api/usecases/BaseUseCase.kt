package com.zuluft.api.usecases

interface BaseUseCase<ARG_TYPE, RETURN_TYPE> {
    operator fun invoke(arg: ARG_TYPE? = null): RETURN_TYPE
}