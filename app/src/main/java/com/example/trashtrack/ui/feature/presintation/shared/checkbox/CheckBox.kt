package com.example.trashtrack.ui.feature.presintation.shared.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography

@Preview
@Composable
private fun CheckboxPreview() {
    val isChecked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Checkbox(
            title = "Test title",
            isChecked = isChecked.value,
            onCheck = { isChecked.value = !isChecked.value }
        )
    }
}

@Composable
fun Checkbox(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: Boolean,
    onCheck: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onCheck
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        CheckboxChecker(
            isChecked = isChecked,
        )
        Text(
            text = title,
            color = Color(0xFF262626),
            style = TTTypography.bodyLarge,
        )
    }
}

@Composable
fun CheckboxChecker(
    isChecked: Boolean
) {

    Box(
        modifier = Modifier
            .size(31.dp, 26.dp)
            .border(width = 1.81.dp, color = Color(0xFF0A0A0A), shape = RoundedCornerShape(4.53.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.check_mark),
                contentDescription = null
            )
        }
    }
}
