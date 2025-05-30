package com.example.bookshelf.ui.screens

import android.text.style.ParagraphStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.ui.viewmodels.BookUiState


@Composable
fun SingleBookScreen(
    singleBookUiState: BookUiState
) {
    when (singleBookUiState) {
        is BookUiState.Loading -> {}
        is BookUiState.Success -> {
            SingleBookCard(
                title = singleBookUiState.book.volumeInfo!!.title!!,
                description = singleBookUiState.book.volumeInfo!!.description!!,
                linkImage = singleBookUiState.book.volumeInfo!!.imageLinks!!.thumbnail!!.replace("http://", "https://")
            )
        }
        is BookUiState.Error -> {
            Text(
                text = singleBookUiState.message
            )
        }
    }
}

@Composable
fun SingleBookCard(
    title: String,
    description: String,
    linkImage: String
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = linkImage,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            style = TextStyle(textIndent = TextIndent(firstLine = 25.sp))
            )
    }
}

@Preview(showBackground = true)
@Composable
fun SingleBookCardPreview() {
    BookshelfTheme {
        SingleBookCard(
            title = "Test my new test of Book",
            description = "«Rock и мир» – уникальная книга, которая содержит в себе информацию обо всех значительных явлениях в мире рок-н-ролла с его рождения в 1955 году до наших дней. На этих страницах история представлена в необычном ракурсе, а для понимания процессов развития рок-музыки приведены важные политические и социальные события, повлиявшие на развитие рока. Но главное в этой книге не чарты и факты, а энергия и дух рок-н-ролла, музыкального жанра, перевернувшего сознание людей на всей планете Земля!",
            linkImage = ""
        )
    }
}