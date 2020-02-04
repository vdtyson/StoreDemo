package com.versilistyson.storedemo

sealed class StoreKey {
    companion object DefaultKeys {
        const val QUOTE_KEY = "QUOTE_KEY"
    }
    data class QuoteKey(
        val key: String = QUOTE_KEY
    )
}