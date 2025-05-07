package com.example.trashtrack.ui.feature.user.map.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.map.ui.bottomsheets.EjectionTimeBS
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.shared.text.textfield.CommentTextField
import com.example.trashtrack.ui.shared.text.textfield.DateTextField
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview(device = "spec:width=411dp,height=891dp")
@Preview(device = "spec:width=673dp,height=841dp")
@Preview
@Composable
private fun DetailsSubscriptionPreview() {
    DetailsSubscription(
        color = MaterialTheme.colors.white
    )
}

@Composable
fun DetailsSubscription(
    color: Color,
) {

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
    var openElectionTimeBS by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val currentIndex by remember { mutableIntStateOf(0) }

    DetailsSubscriptionContent(
        color = color,
        date = date,
        time = time,
        openElectionTimeBS = { openElectionTimeBS = true },
        onDateChange = { date = it },
        kgAndL = Mock.demoKgAndL,
        currentIndex = currentIndex,
    )

    if (openElectionTimeBS){
        TTModalBottomSheet(
            onDismissRequest = { openElectionTimeBS = false }
        ) { hide ->
            EjectionTimeBS(
                times = textList,
                selectedText = time,
                onSave = {select ->
                    hide()
                    time = select
                }
            )
        }
    }
}

@Composable
private fun DetailsSubscriptionContent(
    color: Color,
    date: String,
    time: String,
    onDateChange: (String) -> Unit,
    openElectionTimeBS: () -> Unit,
    kgAndL: List<DataClasses.KgAndL>,
    currentIndex: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingAndBackButton(backButton = {})
        Spacer(modifier = Modifier.height(32.dp))
        PackageAndBags()
        Spacer(modifier = Modifier.height(25.dp))
        SliderBags(
            kgAndL = kgAndL,
            currentIndex = currentIndex
        )
        Spacer(modifier = Modifier.height(22.dp))
        DateTextField(
            modifier = Modifier
                .padding(start = 26.dp, end = 18.dp),
            date = date,
            onDateChange = onDateChange,
        )
        Spacer(modifier = Modifier.height(23.dp))
        EjectionTime(
            openElectionTimeBS = openElectionTimeBS,
            time = time
        )
        Spacer(modifier = Modifier.height(8.dp))
        CommentTextField()
        Spacer(modifier = Modifier.height(42.dp))
        SummaryAndBottom()
        
    }
}

@Composable
private fun HeadingAndBackButton(
    backButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, top = 26.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        BackButton(
            backButton = backButton,
            sizeIcon = 45.dp,
            paddingIcon = 12.dp,
            paddingStart = 0.dp,
            color = MaterialTheme.colors.neutral200
        )
        Text(
            text = "Детали подписки",
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge
        )
    }
}

@Composable
private fun PackageAndBags() {
      Column(
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          Text(
              text = "От пакета из супермаркета",
              color = MaterialTheme.colors.neutral500,
              style = TTTypography.bodyLarge,
              textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
              text = "до большого мусорного мешка",
              color = MaterialTheme.colors.black,
              style = TTTypography.titleLarge,
              textAlign = TextAlign.Center
          )
      }
}

@Composable
fun SliderBags(
    kgAndL: List<DataClasses.KgAndL>,
    currentIndex: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 53.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_big_chevron_left),
            contentDescription = null,
            tint = MaterialTheme.colors.neutral500,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = LocalIndication.current,
                    onClick = { (currentIndex - 1).mod(kgAndL.size) }
                )
        )
        Box(
            modifier = Modifier
                .size(128.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.bag),
                contentDescription = null,
                tint = MaterialTheme.colors.green600,
                modifier = Modifier
                    .matchParentSize()
            )
            kgAndL.getOrNull(currentIndex)?.let { i ->
                Column(
                    modifier = Modifier
                        .padding(bottom = 27.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${i.kg} кг",
                        color = MaterialTheme.colors.white,
                        style = TTTypography.headlineLarge
                    )
                    Text(
                        text = "${i.l} л",
                        color = MaterialTheme.colors.white,
                        style = TTTypography.titleMedium
                    )
                }
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_big_chevron_right),
            contentDescription = null,
            tint = MaterialTheme.colors.neutral500,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = LocalIndication.current,
                    onClick = { (currentIndex + 1) % kgAndL.size }
                )
        )
    }
}

@Composable
fun EjectionTime(
    openElectionTimeBS: () -> Unit,
    time: String
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 31.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Желаемое время выноса",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.titleLarge
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_lucide_pen),
                contentDescription = null,
                tint = MaterialTheme.colors.neutral500,
                modifier = Modifier.clickable(onClick = openElectionTimeBS)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        if (time.isNotEmpty()) {
            Text(
                text = time,
                color = MaterialTheme.colors.black,
                style = TTTypography.titleMedium
            )
        } else{
            Text(
                text = "Выберите время выноса",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleMedium
            )
        }
    }
}

@Composable
fun SummaryAndBottom() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 27.dp,
                bottom = 18.dp,
                end = 21.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Стоимость",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleLarge
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Выгода 80%",
                    color = MaterialTheme.colors.green600,
                    style = TTTypography.headlineSmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1500 ₽",
                        color = MaterialTheme.colors.neutral400,
                        style = TTTypography.titleMedium,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "200 ₽",
                        color = MaterialTheme.colors.black,
                        style = TTTypography.headlineLarge
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(19.dp))
        TTButton(
            onClick = {},
            text = "Перейти к оплате",
        )
    }
}