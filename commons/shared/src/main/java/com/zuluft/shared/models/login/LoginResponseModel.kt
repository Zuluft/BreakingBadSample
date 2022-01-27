package com.zuluft.shared.models.login

data class LoginResponseModel(
    val id: Long,
    val username: String,
    val fullName: String,
    val email: String
)