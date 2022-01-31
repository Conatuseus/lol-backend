package com.conatuseus.lol.config.httpclient

import com.conatuseus.lol.config.properties.HttpProperties
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

class HttpApiCreator<T : Any>(
    client: OkHttpClient,
    properties: HttpProperties,
    objectMapper: ObjectMapper,
    private val clazz: KClass<T>
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(properties.url)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .addConverterFactory(QueryStringConverterFactory.create())
        .client(client)
        .build()

    fun create(): T = retrofit.create(clazz.java)
}

class QueryStringConverterFactory : Converter.Factory() {
    companion object {
        fun create() = QueryStringConverterFactory()
    }

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        if (type == LocalDateTime::class.java) {
            return LocalDateTimeQueryConverter()
        }

        return super.stringConverter(type, annotations, retrofit)
    }
}

class LocalDateTimeQueryConverter : Converter<LocalDateTime, String> {
    override fun convert(value: LocalDateTime): String? {
        return value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}

inline fun <reified T> Call<T>.orElseThrow(handleException: (t: String) -> Throwable): T =
    this.execute().let {
        if (it.isSuccessful) it.body()!! else throw handleException(it.errorBody()!!.string())
    }

inline fun <reified T> Call<T>.orElseNull(): T? =
    this.execute().let {
        if (it.isSuccessful) it.body()!! else null
    }

inline fun <reified T> Call<T>.call(): T = this.execute().let {
    if (it.isSuccessful) it.body()!! else throw RuntimeException(it.errorBody()!!.string())
}
