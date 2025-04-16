package com.example.trashtrack.mock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.trashtrack.R

object Mock{

    private val greenColor = Color(0xFF15803D)
    private val blackColor = Color.Black

    private val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = blackColor)) {
            append("Добро Пожаловать на Trash")
        }
        withStyle(style = SpanStyle(color = greenColor)) {
            append("Track")
        }
        withStyle(style = SpanStyle(color = blackColor)) {
            append("!")
        }
    }

    val demoIntroduction = listOf<DataClasses.Introduction>(


        DataClasses.Introduction(
            backButtonVisible = false,
            skipButtonVisible = true,
            title = text,
            image = R.drawable.scale_12004,
            heading = "Легко избавьтесь от мусора",
            underHeading = "закажите услуги по вывозу мусора по требованию или по расписанию с отслеживанием в реальном времени.",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = true,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Отслеживайте свои грузовики")
                }
            },
            image = R.drawable.scale_12003,
            heading = "Узнайте, где находится ваш грузовик",
            underHeading = "Отслеживайте своего водителя в режиме реального времени",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = true,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Простые заказы и платежи")
                }
            },
            image = R.drawable.scale_12002,
            heading = "Заказ по требованию",
            underHeading = "Легко выбирайте услуги, указывайте время и осуществляйте оплату всего за несколько кликов.",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = false,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Давайте начнём!")
                }
            },
            image = R.drawable.scale_12001,
            heading = "",
            underHeading = "Присоединяйтесь к нам, чтобы получить доступ к простым способам избавления от мусора и отслеживанию ваших заказов в реальном времени!",
            nextButtonVisible = true
        ),

    )

    val demoNews = listOf<DataClasses.NewsMain>(
        DataClasses.NewsMain(
            nameNews = "Новый экологичный сервис по вывозу мусора!",
            image = R.drawable.scale_12001,
            descriptionNews = ""
        ),
        DataClasses.NewsMain(
            nameNews = "Ищем сотрудников в нашу дружную команду",
            image = R.drawable.scale_12001,
            descriptionNews = ""
        ),
        DataClasses.NewsMain(
            nameNews = "Выгодней не найдешь",
            image = R.drawable.scale_12001,
            descriptionNews = ""
        ),
        DataClasses.NewsMain(
            nameNews = "Мы первые ...",
            image = R.drawable.scale_12001,
            descriptionNews = ""
        ),
    )

}