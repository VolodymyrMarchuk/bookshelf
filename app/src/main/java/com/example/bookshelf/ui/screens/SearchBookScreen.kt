package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun SearchBookScreen(
    onButtonSearchClick: (String) -> Unit
) {
    var requestString by remember { mutableStateOf("") }
    var requestAuthor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = requestString,
            label = {
                Text(text = "Request")
            },
            onValueChange = {
                requestString = it
            }
        )
        OutlinedTextField(
            value = requestAuthor,
            label = {
                Text(text = "Author")
            },
            onValueChange = {
                requestAuthor = it
            }
        )
        if (requestString != "") {
            Button(
                onClick = {
                    onButtonSearchClick(requestString)
                },
                modifier = Modifier.padding(top = 15.dp)
            ) {
                Text(text = "Find books")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    BookshelfTheme {
        SearchBookScreen(onButtonSearchClick = {})
    }
}