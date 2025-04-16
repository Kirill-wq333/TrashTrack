package com.example.trashtrack.mock

import androidx.compose.ui.text.AnnotatedString


object DataClasses {
    data class Introduction(
        val backButtonVisible: Boolean,
        val skipButtonVisible: Boolean,
        val title: AnnotatedString,
        val image: Int,
        val heading: String,
        val underHeading: String,
        val nextButtonVisible: Boolean
    )

    data class NewsMain(
        val image: Int,
        val secondImage: Int,
        val nameNews: String,
        val descriptionNews: String
    )
}