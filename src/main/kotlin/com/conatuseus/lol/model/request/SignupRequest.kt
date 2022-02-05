package com.conatuseus.lol.model.request

@SignupConstraint
data class SignupRequest(
    val loginId: String,
    val password: String,
    val confirmPassword: String,
    val nickname: String,
    val email: String?
) {
    fun isNotPasswordSame() = !isPasswordSame()
    fun isPasswordSame() = password == confirmPassword
}
