package com.example.bookshelf.ui.viewmodels

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

data class SearchBook(
    var inauthor: String = "",
    var query: String = "",
    var bookId: String = "",
    var quantity: Int = 1
)

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    var bookListUiState: BookListUiState by mutableStateOf(BookListUiState.Loading)
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)

    private val _searchBookData = MutableStateFlow(SearchBook())
    val searchBookData = _searchBookData.asStateFlow()

    fun saveBookId(id: String) {
        _searchBookData.update { searchData ->
            searchData.copy(
                bookId = id
            )
        }
        receiveBook()
    }

    fun saveRequestGet(
        query: String,
        inauthor: String,
        quantity: Float
        ) {

        val fullRequest = if(inauthor != "") "$query+inauthor:$inauthor".toString() else query

        _searchBookData.update { searchData ->
            searchData.copy(
                query = fullRequest,
                inauthor = inauthor,
                quantity = quantity.toInt()
            )
        }
        receiveBooksList()
    }

    fun receiveBooksList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_searchBookData.value.query != "") {
                val responseBooksList = bookRepository.getBooksList(
                    _searchBookData.value.query,
                    _searchBookData.value.quantity
                    )
                bookListUiState = try {
                    BookListUiState.Success(responseBooksList)
                } catch (e: Exception) {
                    BookListUiState.Error(e.toString())
                }
            }
        }
    }
    fun receiveBook() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_searchBookData.value.bookId != "") {
                val responseBook = bookRepository.getBookData(_searchBookData.value.bookId)
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