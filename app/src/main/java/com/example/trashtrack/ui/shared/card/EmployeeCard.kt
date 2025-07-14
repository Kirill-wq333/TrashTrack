package com.example.trashtrack.ui.shared.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.button.EmployeeButtons
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun EmployeeCardPreview() {
    Surface {
        Column {
            EmployeeCardStreets(
                street = "fdhoihidk"
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun EmployeeCardStreets(
    street: String
) {
    Column {
        EmployeeCardStreetsContent(
            street = street
        )
        Spacer(modifier = Modifier.height(59.dp))
        EmployeeButtons(
            color1 = MaterialTheme.colors.stone50,
            color2 = MaterialTheme.colors.green600,
            colorText1 = MaterialTheme.colors.green600,
            colorText2 = MaterialTheme.colors.white,
            text1 = "Карта",
            text2 = "Открыть отчёт"
        )
    }
}

@Composable
fun EmployeeCardStreetsContent(
    street: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(top = 24.dp, start = 21.dp)
            ) {
                Text(
                    text = street,
                    color = MaterialTheme.colors.neutral800,
                    style = TTTypography.titleLarge
                )
                Spacer(modifier = Modifier.height(29.dp))
                Text(
                    text = "Администрация\n" + "График пн - пт",
                    color = MaterialTheme.colors.primary600,
                    style = TTTypography.titleLarge
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_top),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 11.dp, end = 12.dp)
                    .size(30.dp)
            )
        }
    }
}

@Composable
fun EmployeeCardDriver(
    modifier: Modifier = Modifier
) {
    Column {

    }
}