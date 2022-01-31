package com.conatuseus.lol.controller

import com.conatuseus.lol.service.riotgames.RiotGamesService
import com.conatuseus.lol.service.riotgames.SummonerResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val riotGamesService: RiotGamesService
) {

    private val logger = LoggerFactory.getLogger("protocol")

    @GetMapping
    fun test(): ResponseEntity<String> {
        logger.info("test log!")
        return ResponseEntity.ok("Success!")
    }

    @GetMapping("/{summonerName}")
    fun getSummoner(@PathVariable summonerName: String): ResponseEntity<SummonerResponse> {
        logger.info("summonerName: $summonerName")
        return ResponseEntity.ok(riotGamesService.searchBySummonerName(summonerName))
    }
}
