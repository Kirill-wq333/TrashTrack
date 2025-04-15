package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ReceptionTimePreview() {
    Surface {
        ReceptionTime()
    }
}


@Composable
fun ReceptionTime(
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colors.black)) {
            append("Trash")
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colors.green700)) {
            append("Track")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 39.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            style = TTTypography.displaySmall,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Time()
    }

}

@Composable
fun Time(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.green700,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Мы принимаем заказы",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.green600,
                        shape = RoundedCornerShape(12.dp)
                        ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "10:00-22:00",
                    color = MaterialTheme.colors.white,
                    style = TTTypography.displaySmall,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            bottom = 10.dp,
                            end = 21.dp,
                            top = 10.dp
                        )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "По будням",
                color = MaterialTheme.colors.green600,
                style = TTTypography.titleLarge
            )
        }
    }
}