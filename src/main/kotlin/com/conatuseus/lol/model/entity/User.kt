package com.conatuseus.lol.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    indexes = [
        Index(name = "ux_login_id", columnList = "loginId", unique = true)
    ]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false, length = 20, unique = true)
    val loginId: String,

    @Column(nullable = false, length = 20)
    val password: String,

    @Column(nullable = false, length = 10)
    val nickname: String,

    @Column(nullable = true)
    val email: String? = null
) : BaseTimeEntity()
