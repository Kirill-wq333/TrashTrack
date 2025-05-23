package com.example.trashtrack.ui.feature.user.introduction.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.introduction.ui.components.HeaderIntroduction
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.entrance.EntranceContent
import com.example.trashtrack.ui.feature.employee.introduction.bottomsheet.EntranceEmployee
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.RegistrationContent
import com.example.trashtrack.ui.feature.employee.introduction.bottomsheet.RegistrationEmployeeScreen
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import com.example.trashtrack.ui.theme.spacers

@Preview(device = "spec:width=360dp,height=640dp")
@Preview(device = "spec:width=480dp,height=800dp")
@Preview(device = "spec:width=1440dp,height=2560dp")
@Preview
@Composable
private fun IntroductionPreview() {

    IntroductionScreen(
        introduction = Mock.demoIntroduction,
        openMainScreen = {},
        color = MaterialTheme.colors.white
    )
}

@Composable
fun IntroductionScreen(
    introduction: List<DataClasses.Introduction>,
    openMainScreen: () -> Unit,
    color: Color
) {
    var openEntrance by remember { mutableStateOf(false) }
    var openRegistration by remember { mutableStateOf(false) }
    var openRegisterEmployeeScreen by remember { mutableStateOf(false) }
    var openEntranceEmployeeScreen by remember { mutableStateOf(false) }
    Introduction(
        introduction = introduction,
        openRegistrationScreen = { openRegistration = true },
        openEntranceScreen = { openEntrance = true },
        color = color
    )

    if (openEntrance) {
        TTModalBottomSheet(
            onDismissRequest = { openEntrance = false },
        ) {hide ->
            EntranceContent(
                openEntranceScreen = { openEntranceEmployeeScreen = true; hide() },
                openRegistrationScreen = { openRegistration = true; hide() },
                openScreen = { openMainScreen(); hide() },
                visibleBoxs = true
            )
        }
    }

    if (openRegistration){
        TTModalBottomSheet(
            onDismissRequest = { openRegistration = false },
        ) {hide ->
            RegistrationContent(
                openEntranceScreen = { openEntrance = true; hide() },
                openRegisterEmployeeScreen = { openRegisterEmployeeScreen = true; hide() }
            )
        }
    }

    if (openEntranceEmployeeScreen){
        TTModalBottomSheet(
            onDismissRequest = { openEntranceEmployeeScreen = false }
        ) {hide ->
            EntranceEmployee(
                openRegistrationEmployeeScreen = { openRegisterEmployeeScreen = true; hide() },
                backButton = { hide() }
            )
        }
    }

    if (openRegisterEmployeeScreen){
        TTModalBottomSheet(
            onDismissRequest = { openRegisterEmployeeScreen = false }
        ) {hide ->
            RegistrationEmployeeScreen(
                openEntranceScreen = { openEntrance = true; hide() },
                openEntranceEmployeeScreen = { openEntranceEmployeeScreen = true; hide() },
                backButton = { hide() }
            )
        }
    }
}

@Composable
fun Introduction(
    introduction: List<DataClasses.Introduction>,
    openRegistrationScreen: () -> Unit,
    openEntranceScreen: () -> Unit,
    color: Color
) {
    var activeCircle by remember { mutableIntStateOf(0) }
    val item = introduction.getOrNull(activeCircle) ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = color),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderIntroduction(
                visibleBackButton = item.backButtonVisible,
                visibleSkipButton = item.skipButtonVisible,
                backButton = { activeCircle = (activeCircle - 1).mod(4) },
                skipButton = { activeCircle = introduction.size - 1 }
            )
            Spacer(modifier = Modifier.height(26.dp))
            Content(
                image = item.image,
                title = item.title
            )
            Spacer(modifier = Modifier.height(23.5.dp))
            BottomBar(
                nextButtonVisible = item.nextButtonVisible,
                heading = item.heading,
                underHeading = item.underHeading,
                onClickNext = { activeCircle = (activeCircle + 1) % 4 },
                openEntranceScreen = openEntranceScreen,
                openRegistrationScreen = openRegistrationScreen,
                activeCircle = activeCircle
            )
        }
    }
}

@Composable
private fun Content(
    image: Int,
    title: AnnotatedString
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TTTypography.displaySmall,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.large))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}

@Composable
fun BottomBar(
    nextButtonVisible: Boolean,
    heading: String,
    underHeading: String,
    onClickNext: () -> Unit,
    openEntranceScreen: () -> Unit,
    openRegistrationScreen: () -> Unit,
    activeCircle: Int = 0
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 42.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = heading,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TTTypography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))
        Text(
            text = underHeading,
            color = MaterialTheme.colors.neutral400,
            textAlign = TextAlign.Center,
            style = TTTypography.titleSmall,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xxLarge))
        FourCircular(
            activeCircle = activeCircle,

        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xxLarge))
        TTButtons(
            nextButtonVisible = nextButtonVisible,
            onClickNext = onClickNext,
            openEntranceScreen = openEntranceScreen,
            openRegistrationScreen = openRegistrationScreen
        )
    }
}

@Composable
fun TTButtons(
    nextButtonVisible: Boolean,
    onClickNext: () -> Unit,
    openRegistrationScreen: () -> Unit,
    openEntranceScreen: () -> Unit
) {

    if (nextButtonVisible){
        Column(
            modifier = Modifier
                .padding(start = 22.5.dp, end = 22.5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = openRegistrationScreen)
                    .background(
                        color = Color(0xFF16A34A),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.create_account),
                    color = Color.White,
                    style = TTTypography.headlineLarge,
                    modifier = Modifier
                        .padding(vertical = 13.dp)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))

            Row {
                Text(
                    text = stringResource(R.string.or),
                    color = Color.Black,
                    style = TTTypography.titleLarge,
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    text = stringResource(R.string.entrance),
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline,
                    style = TTTypography.titleLarge,
                    modifier = Modifier.clickable(onClick = openEntranceScreen)
                )
            }

        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClickNext)
                .padding(start = 22.5.dp, end = 22.5.dp)
                .background(
                    color = Color(0xFF16A34A),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.next_screen_button),
                color = Color.White,
                style = TTTypography.headlineLarge,
                modifier = Modifier
                    .padding(vertical = 13.dp)
            )
        }
    }

}

@Composable
fun FourCircular(
    modifier: Modifier = Modifier,
    activeCircle: Int = 0,
) {
    val smallWidth = 8.dp
    val smallHeight = 7.dp
    val largeWidth = 27.dp
    val largeHeight = 7.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) { index ->
            val isActive = index == activeCircle

            val targetWidth by animateDpAsState(
                targetValue = if (isActive) largeWidth else smallWidth,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "circle_width_$index"
            )

            val targetHeight by animateDpAsState(
                targetValue = if (isActive) largeHeight else smallHeight,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "circle_height_$index"
            )

            Box(
                modifier = Modifier
                    .size(width = targetWidth, height = targetHeight)
                    .background(
                        color = if (isActive) Color(0xFF16A34A) else Color(0xFFA3A3A3),
                        shape = CircleShape
                    )
            )
        }
    }
}