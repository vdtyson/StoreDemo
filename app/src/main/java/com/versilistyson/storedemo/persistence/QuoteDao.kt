package com.versilistyson.storedemo.persistence

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuote(quoteToSave: QuoteData)

    @Query("SELECT * FROM quote_table")
    fun readQuote(): Flow<QuoteData>

    @Delete
    fun deleteQuote(quoteToDelete: QuoteData)
}