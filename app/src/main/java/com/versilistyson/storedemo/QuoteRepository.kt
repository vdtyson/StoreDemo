package com.versilistyson.storedemo

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.versilistyson.storedemo.datasource.LocalDataSource
import com.versilistyson.storedemo.datasource.RemoteDataSource
import com.versilistyson.storedemo.persistence.QuoteData
import com.versilistyson.storedemo.util.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class QuoteRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    private val store =
        StoreBuilder
            .fromNonFlow<StoreKey.QuoteKey, QuoteData> { quoteKey ->
                val response = remoteDataSource.getRandomQuote(quoteKey)
                response.body()!!.map()
            }
            .persister(
                reader = localDataSource::getQuote,
                writer = localDataSource::saveQuote
            ).cachePolicy(
                MemoryPolicy.builder().build()
            ).build()

    fun createQuoteStream() =
        store.stream(StoreRequest.cached(StoreKey.QuoteKey(), true))
}