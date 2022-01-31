package com.conatuseus.lol.service.riotgames

data class SummonerResponse(
    val accountId: String,
    val profileId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String,
    val summonerLevel: Long
)
