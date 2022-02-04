package com.conatuseus.lol.service

import com.conatuseus.lol.model.entity.User
import com.conatuseus.lol.model.request.SignupRequest
import com.conatuseus.lol.repository.jpa.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    fun singup(request: SignupRequest): User {
        return userRepository.save(
            User(
                loginId = request.loginId,
                password = request.password,
                nickName = request.nickName,
                email = request.email
            )
        )
    }
}
