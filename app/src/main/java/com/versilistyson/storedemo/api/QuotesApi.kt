package com.versilistyson.storedemo.api

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface QuotesApi {

    @GET("qod.json")
    suspend fun fetchRandomQuote(): Response<DTO.Quote>

    sealed class DTO {
        data class Quote(
            @Json(name = "quote") val quote: String,
            @Json(name = "author") val author: String
        )
    }

    companion object Factory {
        val service =
            Retrofit
                .Builder()
                .baseUrl("https://quotes.rest/")
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder().add(
                            KotlinJsonAdapterFactory()
                        ).build()
                    )
                )
                .build()
                .create(QuotesApi::class.java)
    }
}