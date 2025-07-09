package com.example.trashtrack.ui.shared.map

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.trashtrack.ui.utils.maps.getScaledMarkerDrawable
import org.osmdroid.config.Configuration
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.io.File

@Composable
fun PlacemarkMap(
    modifier: Modifier = Modifier,
    currentPoint: GeoPoint,
    points: List<GeoPoint> = emptyList(),
    initialZoom: Double = 16.0,
    @DrawableRes iconRes: Int? = null,
    iconScale: Float = 1.0f,
    cityBounds: CityBounds? = null,
    flyToCurrent: Boolean = true,
    onPointClick: ((GeoPoint) -> Unit)? = null
) {
    val context = LocalContext.current
    val resources = context.resources
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    var isMapReady by remember { mutableStateOf(false) }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> isMapReady = true
                Lifecycle.Event.ON_STOP -> isMapReady = false
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    if (!isMapReady) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    BaseOSMMap(
        modifier = modifier,
        configuration = {
            val osmConfig = Configuration.getInstance()
            osmConfig.load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
            osmConfig.osmdroidBasePath = File(context.cacheDir, "osmdroid")
            osmConfig.userAgentValue = context.packageName
        },
        mapViewFactoryCallback = { mapView ->
            cityBounds?.let { bounds ->
                mapView.minZoomLevel = 12.0
                mapView.maxZoomLevel = 18.0
                mapView.setScrollableAreaLimitDouble(
                    BoundingBox(
                        bounds.maxLat,
                        bounds.maxLon,
                        bounds.minLat,
                        bounds.minLon
                    )
                )
            }
            mapView.controller.setZoom(initialZoom)
            mapView.controller.setCenter(currentPoint)
        },
        updateCallback = { mapView ->
            mapView.overlays.removeAll { it is Marker }

            iconRes?.let { resId ->
                val icon = getScaledMarkerDrawable(resources, resId, iconScale)
                points.forEach { point ->
                    Marker(mapView).apply {
                        position = point
                        setIcon(icon)
                        onPointClick?.let { listener ->
                            setOnMarkerClickListener { marker, _ ->
                                listener(marker.position)
                                true
                            }
                        }
                        mapView.overlays.add(this)
                    }
                }
            }

            if (flyToCurrent) {
                mapView.controller.animateTo(currentPoint)
            }
            mapView.invalidate()
        }
    )
}

data class CityBounds(
    val minLat: Double,
    val maxLat: Double,
    val minLon: Double,
    val maxLon: Double
)