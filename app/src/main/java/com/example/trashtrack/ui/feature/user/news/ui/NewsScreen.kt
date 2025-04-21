package com.example.trashtrack.ui.feature.user.news.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.main.ui.components.NewsItem
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun NewsScreenPreview() {
    Surface {
        NewsScreen(
            news = Mock.demoNews,
            backButton = {},
            color = Color.White,
            navigateToNews = {}
        )
    }
}

@Composable
fun NewsScreen(
    newsId: Int = 1,
    news: List<DataClasses.NewsMain>,
    backButton: () -> Unit,
    navigateToNews: (Int) -> Unit,
    color: Color
) {
    val currentNews = news.find { it.id == newsId }

    // Handle the case where the news item with the given ID is not found
    if (currentNews == null) {
        Text("News item with ID $newsId not found.", color = Color.Red)
        return // Exit the composable
    }

    val annotatedString = remember(currentNews.descriptionNews) {
        HtmlCompat.fromHtml(currentNews.descriptionNews, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewsMain(
            image = currentNews.image,
            backButton = backButton
        )
        Spacer(modifier = Modifier.height(17.dp))
        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Новости",
                color = MaterialTheme.colors.neutral700,
                style = TTTypography.titleLarge
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = annotatedString,
                color = MaterialTheme.colors.black,
                style = TTTypography.bodyMedium,
                modifier = Modifier
                    .height(320.dp)
            )
            Spacer(modifier = Modifier.height(18.5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.5.dp, end = 28.4.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.neutral800)
                )
                Spacer(modifier = Modifier.height(10.dp))
                NextMainNews(
                    nameNews = currentNews.nameNews,
                    openNews = {
                        val currentIndex = news.indexOfFirst { it.id == newsId }
                        val nextIndex = (currentIndex + 1) % news.size
                        val nextNewsId = news[nextIndex].id
                        Log.d("NewsScreen", "Current ID: $newsId, Next ID: $nextNewsId, Current Index: $currentIndex, Next Index: $nextIndex")
                        navigateToNews(nextNewsId)
                    },
                    image = currentNews.secondImage
                )
            }
        }

    }
}

@Composable
fun NextMainNews(
    nameNews: String,
    openNews: () -> Unit,
    image: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Следующая новость",
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier
                .weight(0.3f)
        )
        Spacer(modifier = Modifier.width(3.dp))
        NewsItem(
            nameNews = nameNews,
            openNews = openNews,
            image = image
        )
    }
}

@Composable
private fun NewsMain(
    image: Int,
    backButton: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(207.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        BackButton(
            backButton = backButton,
            paddingStart = 14.dp,
            paddingTop = 11.dp,
            color = MaterialTheme.colors.white
        )
    }

}