package com.example.bookshelf.data.models


import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    val items: List<Book>
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: BookInfo
)

@Serializable
data class BookInfo(
    val title: String? = null,
    val description: String? = null,
    val imageLinks: BookImage
)

@Serializable
data class BookImage(
    val thumbnail: String? = null
)