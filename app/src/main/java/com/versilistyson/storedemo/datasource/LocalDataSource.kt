package com.versilistyson.storedemo.datasource

import com.versilistyson.storedemo.StoreKey
import com.versilistyson.storedemo.persistence.QuoteDao
import com.versilistyson.storedemo.persistence.QuoteData

class LocalDataSource(private val quoteDao: QuoteDao) {

    fun getQuote(key: StoreKey.QuoteKey) =
        quoteDao.readQuote()

    suspend fun saveQuote(key: StoreKey.QuoteKey, quoteToSave: QuoteData) =
        quoteDao.saveQuote(quoteToSave)

    fun deleteQuote(key: StoreKey.QuoteKey, quoteToDelete: QuoteData) =
        quoteDao.deleteQuote(quoteToDelete)
}