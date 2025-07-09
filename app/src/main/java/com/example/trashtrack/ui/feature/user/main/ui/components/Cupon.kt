package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun CuponPreview() {
    Surface {
        Cupon()
    }
}

@Composable
fun Cupon() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 39.dp, end = 25.dp)
            .background(color = MaterialTheme.colors.green600, shape = RoundedCornerShape(12.dp))
    ){
        TrashBags()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    bottom = 13.dp,
                    end = 25.dp,
                    top = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.59.dp)
        ) {
            Text(
                text = stringResource(R.string.payment_on_ears_trash),
                color = MaterialTheme.colors.white,
                style = TTTypography.headlineLarge
            )
            Box(
                modifier = Modifier
                    .border(width = 3.dp, color = MaterialTheme.colors.white, shape = RoundedCornerShape(32.dp))
            ) {
                Text(
                    text = stringResource(R.string.discount_main),
                    color = MaterialTheme.colors.white,
                    style = TTTypography.titleLarge,
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                            bottom = 10.dp,
                            end = 22.dp
                        )
                )
            }
        }
    }
}

@Composable
fun TrashBags() {
    val tint = MaterialTheme.colors.white.copy(0.45f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.streamline_bag),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .offset(y = 34.6.dp)
                .size(71.6.dp)
                .rotate(60f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.streamline_bag),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .offset(y = -(10).dp)
                .size(71.6.dp)
                .align(Alignment.TopEnd)
                .rotate(60f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.streamline_bag),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .offset(y = 32.4.dp, x = -(31.4).dp)
                .size(71.6.dp)
                .align(Alignment.TopEnd)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.streamline_bag),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .offset(y = 70.dp)
                .size(71.6.dp)
                .align(Alignment.TopEnd)
                .rotate(60f)
        )
    }
}