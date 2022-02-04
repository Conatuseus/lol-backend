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
        Index(name = "idx_login_id", columnList = "loginId")
    ]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val loginId: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val nickName: String,

    @Column(nullable = true)
    val email: String? = null
) : BaseTimeEntity()
