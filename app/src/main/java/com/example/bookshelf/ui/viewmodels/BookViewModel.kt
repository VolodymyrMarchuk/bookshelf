package com.example.bookshelf.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfDataApplication
import com.example.bookshelf.data.models.Book
import com.example.bookshelf.data.models.BookList
import com.example.bookshelf.data.repository.BookRepository
import kotlinx.coroutines.launch

sealed interface BookListUiState {
    data class Success(val bookList: BookList) : BookListUiState
    data class Error(val messageList: String) : BookListUiState
    object Loading : BookListUiState
}
sealed interface BookUiState {
    data class Success(val book: Book) : BookUiState
    data class Error(val message: String) : BookUiState
    object Loading : BookUiState
}

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    var bookListUiState: BookListUiState by mutableStateOf(BookListUiState.Loading)
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)

    var requestGet by mutableStateOf("")
    var bookId by mutableStateOf("")

//init {
//    receiveBooksList()
//}

    fun saveBookId(id: String) {
        bookId = id
        Log.d("saveBookId", bookId)
        receiveBooksList()
    }

    fun saveRequestGet(str: String) {
        requestGet = str
        Log.d("Request: -> ", requestGet)
        receiveBooksList()
    }

    fun receiveBooksList() {
        viewModelScope.launch {

            if (requestGet != "") {
                val responseBooksList = bookRepository.getBooksList(requestGet)
                bookListUiState = try {
                    BookListUiState.Success(responseBooksList)
                } catch (e: Exception) {
                    BookListUiState.Error(e.toString())
                }
            }

            if (bookId != "") {
                val responseBook = bookRepository.getBookData(bookId)
                bookUiState = try {
                    BookUiState.Success(responseBook)
                } catch (e: Exception) {
                    BookUiState.Error(e.toString())
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfDataApplication)
                val bookDataRepository = application.container.bookDataRepository
                BookViewModel(
                    bookRepository = bookDataRepository
                )
            }
        }
    }
}