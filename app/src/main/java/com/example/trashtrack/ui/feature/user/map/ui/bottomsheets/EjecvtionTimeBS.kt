package com.example.trashtrack.ui.feature.user.map.ui.bottomsheets

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
private fun EjectionTimeBSPreview() {
    val textList = listOf(
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
        "19:00",
        "20:00",
        "21:00",
        "22:00"
    )

    var time by remember { mutableStateOf("") }

    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        EjectionTimeBS(
            times = textList,
            selectedText = time,
            onSave = {time = it}
        )
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun EjectionTimeBS(
    onSave: (String) -> Unit,
    times: List<String>,
    selectedText: String
) {
    var selectedIndex by remember {
        mutableIntStateOf(times.indexOf(selectedText).coerceAtLeast(0))
    }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val itemWidth = 50.dp
    val spacing = 16.dp
    val totalItemWidth = with(LocalDensity.current) { (itemWidth + spacing).toPx() }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val padding = (screenWidth - itemWidth) / 2

    val onItemSelected = { index: Int ->
        selectedIndex = index
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Texts()
        Spacer(modifier = Modifier.height(42.dp))
        LeftAndRightChevron(
            items = times,
            scrollState = scrollState,
            onItemSelected = onItemSelected,
            coroutineScope = coroutineScope,
            selectedIndex = selectedIndex,
            totalItemWidth = totalItemWidth
        )
        Spacer(modifier = Modifier.height(14.dp))
        Times(
            times = times,
            selectedIndex = selectedIndex,
            scrollState = scrollState,
            padding = padding,
            itemWidth = itemWidth,
            onItemSelected = onItemSelected,
        )
        Spacer(modifier = Modifier.height(28.dp))
        TTButton(
            modifier = Modifier
                .padding(start = 18.dp,end = 26.dp, bottom = 23.dp),
            text = "Указать",
            onClick = { onSave(times[selectedIndex])  }
        )
    }
}

@Composable
fun Texts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Выберите время",
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.headlineLarge
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "Желаемое время выноса мусора .\n" +
                    "Курьер  выполнит заказ в течении часа от указанного времени",
            color = MaterialTheme.colors.neutral800,
            style = TTTypography.bodyMedium
        )
    }
}

@Composable
fun LeftAndRightChevron(
    items: List<String>,
    scrollState: ScrollState,
    onItemSelected: (Int) -> Unit,
    coroutineScope: CoroutineScope,
    selectedIndex: Int,
    totalItemWidth: Float
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_big_chevron_left),
            contentDescription = null,
            tint = MaterialTheme.colors.neutral500,
            modifier = Modifier
                .clickable {
                    coroutineScope.launch {
                        val newIndex = (selectedIndex - 1).coerceAtLeast(0)
                        onItemSelected(newIndex)
                        scrollState.animateScrollTo((newIndex * totalItemWidth).toInt())
                    }
                }
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_big_chevron_right),
            contentDescription = null,
            tint = MaterialTheme.colors.neutral500,
            modifier = Modifier
                .clickable{
                    coroutineScope.launch {
                        val newIndex = (selectedIndex + 1).coerceAtMost(items.size - 1)
                        onItemSelected(newIndex)
                        scrollState.animateScrollTo((newIndex * totalItemWidth).toInt())
                    }
                }
        )
    }
}

@Composable
private fun Times(
    times: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    scrollState: ScrollState,
    padding: Dp,
    itemWidth: Dp,
) {

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(padding - itemWidth / 2))

        times.forEachIndexed { index, i ->
            Box(
                modifier = Modifier
                    .clickable { onItemSelected(index) }
                    .background(
                        color = if (index == selectedIndex) MaterialTheme.colors.neutral200
                        else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    text = i,
                    color = MaterialTheme.colors.primary600,
                    style = TTTypography.headlineLarge
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        Spacer(modifier = Modifier.width(padding - itemWidth / 2))
    }
}