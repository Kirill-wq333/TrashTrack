package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun NewsMain(
    newsMain: List<DataClasses.NewsMain>,
    openNews: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.6.dp)
    ) {
        Text(
            text = "Новости",
            color = MaterialTheme.colors.neutral900,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(11.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(newsMain) { item ->
                NewsItem(
                    nameNews = item.nameNews,
                    openNews = openNews,
                    image = item.image
                )
            }
        }
    }

}

@Composable
fun NewsItem(
    nameNews: String,
    openNews: () -> Unit,
    image: Int = 0
) {
    Column(
        modifier = Modifier
            .clickable(onClick = openNews)
            .width(130.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(69.dp)
                .clip(shape = RoundedCornerShape(11.dp))
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Новости",
            color = MaterialTheme.colors.neutral700,
            style = TTTypography.bodyMedium
        )

        Text(
            text = nameNews,
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.bodySmall
        )
    }
}