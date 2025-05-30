package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun SearchBookScreen(
    onButtonSearchClick: (String, String, Float) -> Unit
) {
    var requestString by remember { mutableStateOf("") }
    var requestAuthor by remember { mutableStateOf("") }
    var requestQuantity by remember { mutableFloatStateOf(1f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = requestString,
            label = {
                Text(text = "Title")
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
        Text(
            text = "Search quantity: ${requestQuantity.toInt()}",
            modifier = Modifier.padding(top = 30.dp)
            )
        Slider(
            value = requestQuantity,
            valueRange = 1f..40f,
            steps = 8,
            onValueChange = {
                requestQuantity = it
            },
            modifier = Modifier.padding(5.dp)
        )

        if (requestString != "") {
            Button(
                onClick = {
                    onButtonSearchClick(
                        requestString,
                        requestAuthor,
                        requestQuantity
                        )
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
        SearchBookScreen(onButtonSearchClick = { Str1, Str2, Qty ->
        })
    }
}