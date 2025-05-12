package com.example.trashtrack.ui.shared.map

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.trashtrack.ui.utils.maps.getScaledMarkerDrawable
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

@Composable
fun PlacemarkMap(
    modifier: Modifier,
    currentPoint: GeoPoint,
    points: List<GeoPoint> = emptyList(),
    initialZoom: Double = 16.0,
    @DrawableRes iconRes: Int? = null,
    iconScale: Float = 1.0f,
    flyToCurrent: Boolean = true,
    onPointClick: ((GeoPoint) -> Unit)? = null
) {

    val context = LocalContext.current
    val resources = context.resources

    val markerIcon = remember(iconRes, iconScale) {
        if (iconRes != null) {
            getScaledMarkerDrawable(resources, iconRes, iconScale)
        } else {
            null
        }
    }

    val markerTapListener = remember(onPointClick) {
        onPointClick?.let { click ->
            Marker.OnMarkerClickListener { marker, mapView ->
                val position = marker.position
                mapView.controller.animateTo(position)
                click(position)
                true
            }
        }
    }


    BaseOSMMap(
        modifier = modifier,
        mapViewFactoryCallback = { mapView ->
            mapView.controller.apply {
                setZoom(initialZoom)
                setCenter(currentPoint)
            }
        },
        updateCallback = { view ->
            if (flyToCurrent) {
                view.controller.apply {
                    animateTo(currentPoint)
                }
            }

            view.overlays.removeAll { it is Marker }

            if (iconRes != null) {
                val markers = points.map { point ->
                    Marker(view).apply {
                        position = point
                        icon = markerIcon
                        if (markerTapListener != null) {
                            setOnMarkerClickListener(markerTapListener)
                        }
                    }
                }
                view.overlays.addAll(markers)
            }
        }
    )
}