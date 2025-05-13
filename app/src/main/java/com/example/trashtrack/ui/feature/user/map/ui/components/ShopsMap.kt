package com.example.trashtrack.ui.feature.user.map.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.map.CityBounds
import com.example.trashtrack.ui.shared.map.PlacemarkMap
import org.osmdroid.util.GeoPoint

@Composable
fun Map(
    modifier: Modifier = Modifier,
    selectedShopPoint: GeoPoint?,
    onShopClick: (GeoPoint) -> Unit
) {

    val tolyattiBounds = remember {
        CityBounds(
            minLat = 53.0,
            maxLat = 53.9,
            minLon = 49.0,
            maxLon = 49.9
        )
    }

    PlacemarkMap(
        modifier = modifier,
        currentPoint = selectedShopPoint ?: GeoPoint(53.53, 49.34), // Центр Тольятти
        points = selectedShopPoint?.let { listOf(it) } ?: emptyList(),
        initialZoom = 14.0,
        cityBounds = tolyattiBounds,
        iconRes = R.drawable.shop_map_point,
        iconScale = 0.1f,
        onPointClick = onShopClick
    )
}