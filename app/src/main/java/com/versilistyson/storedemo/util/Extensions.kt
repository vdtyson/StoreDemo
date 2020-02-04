package com.versilistyson.storedemo.util

import com.versilistyson.storedemo.api.QuotesApi
import com.versilistyson.storedemo.persistence.QuoteData

fun QuotesApi.DTO.Quote.map() : QuoteData =
    QuoteData(
        quote = quote,
        author = author
    )