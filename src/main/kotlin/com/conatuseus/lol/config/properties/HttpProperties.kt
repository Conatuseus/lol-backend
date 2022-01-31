package com.conatuseus.lol.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("lol.client.http")
interface HttpProperties {
    val url: String
    val maxIdleConnections: Int
        get() = Runtime.getRuntime().availableProcessors()
    val keepAliveTime: Long
        get() = 60000
    val connectionTimeout: Long
        get() = 5000
    val socketTimeout: Long
        get() = 30000
}
