package com.conatuseus.lol.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    private val logger = LoggerFactory.getLogger("protocol")

    @GetMapping
    fun test(): String {
        logger.info("test")
        return "Success"
    }
}
