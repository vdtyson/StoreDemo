package com.versilistyson.storedemo.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "quote_table"
)
data class QuoteData(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "author") val author: String
)