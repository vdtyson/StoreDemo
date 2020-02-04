package com.versilistyson.storedemo.datasource

import com.versilistyson.storedemo.StoreKey
import com.versilistyson.storedemo.api.QuotesApi

class RemoteDataSource(private val api: QuotesApi) {
    suspend fun getRandomQuote(key: StoreKey.QuoteKey) =
        api.fetchRandomQuote()
}