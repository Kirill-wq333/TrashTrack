package com.example.trashtrack.ui.feature.presintation.introduction.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography

@Composable
fun HeaderIntroduction(
    visibleBackButton: Boolean,
    visibleSkipButton: Boolean,
    backButton: () -> Unit,
    skipButton: () -> Unit

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 40.5.dp,
                start = 42.6.dp,
                end = 30.15.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (visibleBackButton) {
            Box(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = backButton
                        )
                        .background(
                            color = Color(0xFFF6F6F5),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.left_chevron_button),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(9.dp)
                    )
                }
            }
        }

        if (visibleSkipButton) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Пропустить",
                    style = TTTypography.titleLarge,
                    color = Color(0xFF262626),
                    modifier = Modifier
                        .clickable(
                            onClick = skipButton
                        )
                        .padding(top = 7.5.dp, bottom = 12.5.dp)
                )
            }
        }
    }
}