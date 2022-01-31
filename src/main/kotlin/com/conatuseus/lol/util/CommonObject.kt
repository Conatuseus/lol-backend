package com.conatuseus.lol.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.core.env.Environment

val camelObjectMapper = jacksonObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

fun Environment.isDev(): Boolean {
    return acceptsProfiles { it.test("local") }
}
