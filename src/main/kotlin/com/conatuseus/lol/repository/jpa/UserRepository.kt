package com.conatuseus.lol.repository.jpa

import com.conatuseus.lol.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
