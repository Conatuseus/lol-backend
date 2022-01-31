package com.conatuseus.lol.config.httpclient

import com.conatuseus.lol.config.properties.HttpProperties
import com.conatuseus.lol.util.isDev
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.util.concurrent.TimeUnit

class HttpClientBuilder(
    httpProperties: HttpProperties
) {
    private val maxIdleConnections: Int = httpProperties.maxIdleConnections
    private val keepAliveTime: Long = httpProperties.keepAliveTime
    private val socketTimeout: Long = httpProperties.socketTimeout
    private val connectionTimeout: Long = httpProperties.connectionTimeout

    // Content-Type은 json으로 기본값 세팅
    private val headers = mutableMapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON_VALUE)
    private val interceptors = mutableListOf<Interceptor>()

    private val headerInterceptor
        get() = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            headers.forEach {
                builder.addHeader(it.key, it.value)
            }
            chain.proceed(builder.build())
        }

    fun addHeader(header: Pair<String, String>): HttpClientBuilder = headers.put(header.first, header.second).let { this }

    fun addInterceptor(interceptor: Interceptor): HttpClientBuilder = interceptors.add(interceptor).let { this }

    fun addInterceptorIfDev(environment: Environment, interceptor: Interceptor): HttpClientBuilder =
        if (environment.isDev()) addInterceptor(interceptor)
        else this

    fun build(): OkHttpClient = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(maxIdleConnections, keepAliveTime, TimeUnit.MILLISECONDS))
        .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        .readTimeout(socketTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(socketTimeout, TimeUnit.MILLISECONDS)
        .addInterceptors(mutableListOf(headerInterceptor) + interceptors)
        .build()

    private fun OkHttpClient.Builder.addInterceptors(
        interceptors: List<Interceptor>
    ): OkHttpClient.Builder {
        interceptors.forEach { addInterceptor(it) }
        return this
    }
}
