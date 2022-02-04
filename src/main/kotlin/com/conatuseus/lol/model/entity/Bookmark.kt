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
        Index(name = "ux_user_id_loler_id", columnList = "userId, lolerId", unique = true)
    ]
)
data class Bookmark(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val lolerId: Long,

    @Column(nullable = true)
    val memo: String = ""
) : BaseTimeEntity()
