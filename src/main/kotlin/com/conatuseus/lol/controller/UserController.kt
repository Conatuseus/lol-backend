package com.conatuseus.lol.controller

import com.conatuseus.lol.model.dto.toDto
import com.conatuseus.lol.model.request.SignupRequest
import com.conatuseus.lol.model.response.UserResponse
import com.conatuseus.lol.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users/v1")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignupRequest): UserResponse {
        val user = userService.singup(request)
        return UserResponse(user.toDto())
    }
}
