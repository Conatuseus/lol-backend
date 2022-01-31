package com.conatuseus.lol.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Loler(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val summonerId: String,

    val summonerName: String,

    val summonerNameWithoutBlank: String,

    val puuid: String,

    val accountId: String,

    val summonerLevel: Long
) : BaseTimeEntity()
