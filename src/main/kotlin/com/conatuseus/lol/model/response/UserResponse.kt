package com.conatuseus.lol.model.response

import com.conatuseus.lol.model.dto.UserDto

data class UserResponse(
    val user: UserDto?
) : DefaultResponse()
