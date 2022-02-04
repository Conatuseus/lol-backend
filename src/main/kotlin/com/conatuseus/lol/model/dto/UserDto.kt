package com.conatuseus.lol.model.dto

import com.conatuseus.lol.model.entity.User

data class UserDto(
    val id: Long,
    val loginId: String,
    val nickname: String,
    val email: String? = null
) {
    constructor(user: User) : this(
        id = user.id,
        loginId = user.loginId,
        nickname = user.nickname,
        email = user.email
    )
}

fun User?.toDto() = this?.let { UserDto(it) }
