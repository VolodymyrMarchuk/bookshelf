package com.example.bookshelf.data.models


import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    val items: List<Book>? = null
)

@Serializable
data class Book(
    val id: String? = null,
    val volumeInfo: BookInfo? = BookInfo()
)

@Serializable
data class BookInfo(
    val title: String? = null,
    val description: String? = null,
    val imageLinks: BookImage? = BookImage()
)

@Serializable
data class BookImage(
    val thumbnail: String? = ""
)