package com.conatuseus.lol.model.request

@PasswordConstraint
data class SignupRequest(
    val loginId: String,
    val password: String,
    val confirmPassword: String,
    val nickName: String,
    val email: String?
) {
    fun isNotPasswordSame() = !isPasswordSame()
    fun isPasswordSame() = password == confirmPassword
}
