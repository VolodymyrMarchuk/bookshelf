package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.container.BookContainer
import com.example.bookshelf.data.container.DefaultAppContainer

class BookshelfDataApplication : Application() {
    lateinit var container: BookContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}