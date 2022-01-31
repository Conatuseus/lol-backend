package com.conatuseus.lol.service.riotgames

import com.conatuseus.lol.config.httpclient.orElseThrow
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class RiotGamesService(
    private val riotGamesHttpApi: RiotGamesHttpApi
) {
    fun searchBySummonerName(summonerName: String): SummonerResponse {
        return riotGamesHttpApi.getSummonerBySummonerName(summonerName)
            .orElseThrow { IllegalArgumentException("error!") }
    }
}
