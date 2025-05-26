package com.example.bookshelf.data.container

import com.example.bookshelf.data.api.BookApiService
import com.example.bookshelf.data.api.ListBooksApiService
import com.example.bookshelf.data.repository.BookRepository
import com.example.bookshelf.data.repository.NetworkBookDataRepository
import com.example.bookshelf.ui.viewmodels.BookViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.Retrofit

interface BookContainer {
    val bookDataRepository: BookRepository
}

class DefaultAppContainer(
) : BookContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    private val retrofitService2: ListBooksApiService by lazy {
        retrofit.create(ListBooksApiService::class.java)
    }

    override val bookDataRepository: BookRepository by lazy {
        NetworkBookDataRepository(
            retrofitService,
            retrofitService2
        )
    }
}