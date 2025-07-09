package com.example.trashtrack.ui.feature.user.map.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.feature.user.map.ui.bottomsheets.SearchAddressOnMap
import com.example.trashtrack.ui.feature.user.map.ui.components.Map
import com.example.trashtrack.ui.shared.button.back.BackButton
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen(
    color: Color,
    nextOpenData: () -> Unit,
    backButton: () -> Unit,
    currentShopPoint: GeoPoint? = GeoPoint(53.53, 49.34)
) {
    Scaffold() { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = color)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Map(
                    modifier = Modifier
                        .weight(.95f),
                    selectedShopPoint = currentShopPoint,
                    onShopClick = { clickedPoint ->
                        println("Clicked on: $clickedPoint")

                    }
                )
                Box(
                    modifier = Modifier
                        .weight(.5f)
                        .background(
                            color = color,
                            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                ) {
                    SearchAddressOnMap(
                        modifier = Modifier,
                        nextOpenData = nextOpenData
                    )
                }
            }
            BackButton(
                modifier = Modifier.align(Alignment.TopStart),
                paddingStart = 19.dp,
                backButton = backButton
            )
        }
    }
}