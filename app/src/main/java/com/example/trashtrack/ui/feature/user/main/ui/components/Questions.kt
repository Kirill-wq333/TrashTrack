package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun QuestionsPreview() {
    Surface {
        Questions()
    }
}

@Composable
fun Questions() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 39.dp, end = 25.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.green700,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 5.8.dp, horizontal = 12.5.dp)
        ) {
            Text(
                text = "Остались вопросы?",
                color = MaterialTheme.colors.neutral950,
                style = TTTypography.headlineLarge
            )
            Row(
                modifier = Modifier
                    .padding(start = 7.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Нажмите  здесь для связи с ТЕХ.ПОДДЕРЖКОЙ",
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.green600,
                    style = TTTypography.titleLarge,
                    modifier = Modifier
                        .weight(0.6f)
                )
                Spacer(modifier = Modifier.width(51.dp))
                Image(
                    painter = painterResource(R.drawable.questions),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp,88.dp)
                        .weight(0.4f)
                )
            }
        }
    }

}