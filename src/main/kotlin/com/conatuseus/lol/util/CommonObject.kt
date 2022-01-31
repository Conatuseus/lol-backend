package com.conatuseus.lol.util

import org.springframework.core.env.Environment

fun Environment.isDev(): Boolean {
    return acceptsProfiles { it.test("local") }
}
