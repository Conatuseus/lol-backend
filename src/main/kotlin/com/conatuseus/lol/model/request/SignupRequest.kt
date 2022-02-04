package com.conatuseus.lol.model.request

data class SignupRequest(
    val loginId: String,
    val password: String,
    val nickName: String,
    val email: String?
)
