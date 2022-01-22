package com.conatuseus.lol.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    private val logger = LoggerFactory.getLogger("protocol")

    @GetMapping
    fun test(): ResponseEntity<String> {
        logger.info("test log!")
        return ResponseEntity.ok("Success!")
    }
}
