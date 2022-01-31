package com.conatuseus.lol.config

import com.conatuseus.lol.config.httpclient.HttpApiCreator
import com.conatuseus.lol.config.httpclient.HttpClientBuilder
import com.conatuseus.lol.config.properties.RiotGamesProperties
import com.conatuseus.lol.service.riotgames.RiotGamesHttpApi
import com.conatuseus.lol.util.camelObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@Configuration
class HttpApiConfig(
    private val objectMapper: ObjectMapper,
    private val environment: Environment
) {

    @Bean
    fun riotGamesHttpApi(riotGamesProperties: RiotGamesProperties): RiotGamesHttpApi =
        HttpApiCreator(
            HttpClientBuilder(riotGamesProperties)
                .addHeader("X-Riot-Token" to riotGamesProperties.apiKey)
                .addHeader(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .addInterceptorIfDev(environment, HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build(),
            riotGamesProperties,
            camelObjectMapper,
            RiotGamesHttpApi::class
        ).create()
}
