package com.example.trashtrack.ui.utils.maps

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale

fun getScaledMarkerDrawable(resources: Resources, @DrawableRes resId: Int, scale: Float): Drawable {
    // Получаем оригинальный Drawable
    val drawable = ResourcesCompat.getDrawable(resources, resId, null) ?:
    throw IllegalArgumentException("Drawable resource not found")

    // Рассчитываем новые размеры с учетом масштаба
    val width = (drawable.intrinsicWidth * scale).coerceAtLeast(1F) // Минимум 1 пиксель
    val height = (drawable.intrinsicHeight * scale).coerceAtLeast(1F)

    // Создаем BitmapDrawable для лучшего контроля масштабирования
    return BitmapDrawable(resources,
        (drawable.toBitmap()).copy(Bitmap.Config.ARGB_8888, true)
            .scale(width.toInt(), height.toInt())
    ).apply {
        // Устанавливаем границы для правильного отображения
        setBounds(0, 0, width.toInt(), height.toInt())
    }
}