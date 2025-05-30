package com.example.bookshelf.data.api

import com.example.bookshelf.data.models.Book
import com.example.bookshelf.data.models.BookList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BookApiService {
    @GET("volumes/{id}")
    suspend fun getBookData(
        @Path("id") id: String
    ) : Book
}

interface ListBooksApiService {
    @GET("volumes")
    suspend fun getBooksList(
        @Query("q", encoded = true) searchQuery: String,
        @Query("maxResults") qty: Int
    ) : BookList
}
