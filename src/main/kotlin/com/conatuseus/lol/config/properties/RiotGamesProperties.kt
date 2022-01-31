package com.conatuseus.lol.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("lol.client.riot-games")
class RiotGamesProperties(
    override val url: String,
    val apiKey: String = "RGAPI-08b6a892-875a-492f-8af9-08d5290b2b3c" // 라이엇에 Product 등록 전까지는 임시 API KEY 사용
) : HttpProperties
