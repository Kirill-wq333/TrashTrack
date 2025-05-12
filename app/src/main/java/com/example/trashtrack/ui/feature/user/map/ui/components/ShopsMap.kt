package com.example.trashtrack.ui.feature.user.map.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.trashtrack.ui.shared.map.PlacemarkMap
import com.example.trashtrack.R
import org.osmdroid.util.GeoPoint

@Composable
fun Map(
    modifier: Modifier,
    selectedShopPoint: GeoPoint?,
    onShopClick: (GeoPoint) -> Unit
) {

    if (selectedShopPoint != null) {
        PlacemarkMap(
            modifier = modifier,
            currentPoint = selectedShopPoint,
            points = listOf(selectedShopPoint),
            initialZoom = 15.0,
            iconRes = R.drawable.shop_map_point,
            iconScale = 0.25f,
            onPointClick = onShopClick
        )
    } else {
        PlacemarkMap(
            modifier = modifier,
            currentPoint = GeoPoint(0.0, 0.0),
            initialZoom = 2.0,
            points = emptyList(),
            iconRes = null,
            iconScale = 0.25f,
            onPointClick = null
        )
    }

}