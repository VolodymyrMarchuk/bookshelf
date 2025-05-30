package com.example.bookshelf.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.R
import com.example.bookshelf.ui.viewmodels.BookViewModel

enum class BookshelfScreensApp (@StringRes val title: Int) {
    Search(title = R.string.search),
    ListBook(title = R.string.list_books),
    Book(title = R.string.single_book),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Google Bookshelf")
        }
    )
}

@Composable
fun BookshelfApp(
    bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { BookshelfAppBar() }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BookshelfScreensApp.Search.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BookshelfScreensApp.Search.name) {
                SearchBookScreen(onButtonSearchClick = {query, author, quantity ->
                    bookViewModel.saveRequestGet(query, author, quantity)
                    navController.navigate(BookshelfScreensApp.ListBook.name)
                }
                )
            }

            composable(route = BookshelfScreensApp.ListBook.name) {
                ListBookScreen(
                    bookViewModel.bookListUiState,
                    onImageClick = {
                        bookViewModel.saveBookId(it)
                        navController.navigate(BookshelfScreensApp.Book.name)
                    })
            }

            composable(route = BookshelfScreensApp.Book.name) {
                SingleBookScreen(
                    bookViewModel.bookUiState,
                )
            }
        }
    }
}