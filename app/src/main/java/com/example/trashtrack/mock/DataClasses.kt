package com.example.trashtrack.mock

import androidx.compose.ui.graphics.Color
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
        val id: Int,
        val image: Int,
        val secondImage: Int,
        val nameNews: String,
        val descriptionNews: String
    )

    data class KgAndL(
        val kg: Int,
        val l: Int
    )

    data class SubscriptionData(
        val benefit: String,
        val heading: String,
        val underHeading: String,
        val money: Int,
        val price: Int,
        val visible: Boolean,
        val background: Color,
        val border: Color,
        val text: Color
    )

    data class SubscriptionDetails(
        val date: String,
        val time: String,
        val comment: String,
        val subscriptionData: SubscriptionData?
    )
}