package com.example.trashtrack.ui.shared.text.transform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

private class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (mask.indices.filter { mask[it] != '#' }
                    .contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }; out += char; maskIndex++
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val offsetValue = offset.absoluteValue
                    if (offsetValue == 0) return 0
                    var numberOfHashtags = 0
                    val masked = mask
                        .takeWhile {
                            if (it == '#') numberOfHashtags++
                            numberOfHashtags < offsetValue
                        }
                    return (masked.length + 1)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return mask
                        .take(offset.absoluteValue)
                        .count { it == '#' }
                }
            }
        )

    }

}

@Composable
fun rememberMaskVisualTransformation(mask: String): VisualTransformation =
    remember(mask) { MaskVisualTransformation(mask) }