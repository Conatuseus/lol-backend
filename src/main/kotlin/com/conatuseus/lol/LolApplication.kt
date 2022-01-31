package com.conatuseus.lol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan("com.conatuseus.lol.config")
class LolApplication

fun main(args: Array<String>) {
    runApplication<LolApplication>(*args)
}
