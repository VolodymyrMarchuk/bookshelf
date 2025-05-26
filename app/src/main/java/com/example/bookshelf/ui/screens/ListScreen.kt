package com.example.bookshelf.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.bookshelf.R
import com.example.bookshelf.ui.viewmodels.BookListUiState

@Composable
fun ListBookScreen(
    bookListUiState: BookListUiState,
    onImageClick: (String) -> Unit,
    modifier: Modifier) {
    when (bookListUiState) {
        is BookListUiState.Loading -> {}
        is BookListUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(bookListUiState.bookList.items) { book ->
                        BookImageCard(
                            linkPhoto =  book.volumeInfo.imageLinks.thumbnail!!.replace("http://", "https://"),
                            descriptionPhoto = book.id,
                            onClick = {onImageClick(book.id)}
                        )
                    }
                }
        }
        is BookListUiState.Error -> {
            Text(
                text = bookListUiState.messageList
            )
        }
    }
}

@Composable
fun BookImageCard(
    linkPhoto: String,
    descriptionPhoto: String,
    onClick: () -> Unit
) {
    AsyncImage(
        model = linkPhoto,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.connection_error),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = descriptionPhoto,
        modifier = Modifier.clickable(onClick = onClick)
    )
}
