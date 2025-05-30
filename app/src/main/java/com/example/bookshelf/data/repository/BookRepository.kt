package com.example.bookshelf.data.repository

import com.example.bookshelf.data.api.BookApiService
import com.example.bookshelf.data.api.ListBooksApiService
import com.example.bookshelf.data.models.Book
import com.example.bookshelf.data.models.BookList


interface BookRepository {
    suspend fun getBookData(id: String) : Book
    suspend fun getBooksList(query: String, qty: Int) : BookList
}

class NetworkBookDataRepository(
    private val bookApiService: BookApiService,
    private val listBooksApiService: ListBooksApiService
) : BookRepository {
    override suspend fun getBookData(id: String): Book = bookApiService.getBookData(id)
    override suspend fun getBooksList(query: String, qty: Int): BookList = listBooksApiService.getBooksList(query, qty)
}