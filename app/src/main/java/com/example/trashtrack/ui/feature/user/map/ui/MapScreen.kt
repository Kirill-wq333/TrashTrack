package com.example.trashtrack.ui.feature.user.map.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.feature.user.map.ui.bottomsheets.SearchAddressOnMap
import com.example.trashtrack.ui.feature.user.map.ui.components.Map
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.back.BackButton
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen(
    color: Color,
    nextOpenData: () -> Unit,
    backButton: () -> Unit
) {
    val currentShopPoint by remember { mutableStateOf<GeoPoint?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        Map(
            modifier = Modifier.fillMaxSize(),
            selectedShopPoint = currentShopPoint,
            onShopClick = {}
        )
        Content(
            nextOpenData = nextOpenData,
            backButton = backButton
        )
    }
}

@Composable
fun Content(
    nextOpenData: () -> Unit,
    backButton: () -> Unit
) {
    var openSearchAddress by remember { mutableStateOf(true) }


  Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.SpaceBetween
  ) {
      BackButton(
          paddingStart = 19.dp,
          paddingTop = 22.dp,
          backButton = backButton
      )
      TTModalBottomSheet(
          onDismissRequest = { openSearchAddress = false }
      ) {
          SearchAddressOnMap(
              nextOpenData = nextOpenData
          )
      }
  }
}