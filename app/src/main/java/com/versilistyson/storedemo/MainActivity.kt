package com.versilistyson.storedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.storedemo.api.QuotesApi
import com.versilistyson.storedemo.datasource.LocalDataSource
import com.versilistyson.storedemo.datasource.RemoteDataSource
import com.versilistyson.storedemo.persistence.QuoteData
import com.versilistyson.storedemo.persistence.QuoteDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = QuoteDatabase.getInstance(this@MainActivity)
        val quoteService = QuotesApi.service
        val localDataSource = LocalDataSource(database.quoteDao())
        val remoteDataSource = RemoteDataSource(quoteService)
        val repository = QuoteRepository(localDataSource, remoteDataSource)
        button_generateQuote.setOnClickListener {
            val quoteCollector: FlowCollector<StoreResponse<QuoteData>> = object : FlowCollector<StoreResponse<QuoteData>> {
                override suspend fun emit(value: StoreResponse<QuoteData>) {


                    when(value) {
                        is StoreResponse.Data -> {
                            textView_newQuote.text = value.value.content
                        }
                        is StoreResponse.Error -> {
                            textView_newQuote.text = value.error.message
                        }
                    }
                }

            }
            lifecycleScope.launch {

                repository.createQuoteStream().collect(quoteCollector)
            }
        }
    }

}
