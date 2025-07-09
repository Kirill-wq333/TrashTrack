package com.example.trashtrack.ui.shared.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.trashtrack.ui.theme.colors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TTModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.(hide: () -> Unit) -> Unit
) {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            containerColor = MaterialTheme.colors.white
        ) {

            val hide = {
                scope.launch {
                    sheetState.hide()
                    onDismissRequest()
                }; Unit
            }

            content.invoke(this, hide)
        }
    }
}