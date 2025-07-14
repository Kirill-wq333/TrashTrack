package com.example.trashtrack.ui.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun EmployeeButtonsPreview() {
    Surface {
        Column {
            EmployeeButtons(
                color1 = MaterialTheme.colors.stone50,
                color2 = MaterialTheme.colors.green600,
                colorText1 = MaterialTheme.colors.green600,
                colorText2 = MaterialTheme.colors.white,
                text1 = "Карта",
                text2 = "Открыть отчёт"
            )
            Spacer(modifier = Modifier.height(50.dp))
            EmployeeButtonsContent(
                color1 = MaterialTheme.colors.stone50,
                color2 = MaterialTheme.colors.green600,
                colorText1 = MaterialTheme.colors.green600,
                colorText2 = MaterialTheme.colors.white,
                text1 = "Карта",
                text2 = "Открыть отчёт"
            )
        }
    }
}

@Composable
fun EmployeeButtons(
    color1: Color,
    color2: Color,
    colorText1: Color,
    colorText2: Color,
    text1: String,
    text2: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, MaterialTheme.colors.primary600)
        )
        EmployeeButtonsContent(
            color1 = color1,
            color2 = color2,
            colorText1 = colorText1,
            colorText2 = colorText2,
            text1 = text1,
            text2 = text2
        )
    }
}

@Composable
fun EmployeeButtonsContent(
    color1: Color,
    color2: Color,
    colorText1: Color,
    colorText2: Color,
    text1: String,
    text2: String
) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EmployeeButtonItem(
                color = color1,
                colorText = colorText1,
                text = text1,
                modifier = Modifier
                    .weight(0.2f)
            )
            EmployeeButtonItem(
                color = color2,
                colorText = colorText2,
                text = text2,
                modifier = Modifier
            )
        }

}


@Composable
fun EmployeeButtonItem(
    modifier: Modifier,
    color: Color,
    text: String,
    colorText: Color
) {
    Box(
        modifier = modifier
            .background(color = color),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            color = colorText,
            style = TTTypography.headlineLarge,
            modifier = Modifier
                .padding(horizontal = 26.dp, vertical = 20.dp)
        )
    }
}