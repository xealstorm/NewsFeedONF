package com.onefootball.presentation.news.model

data class NewsItem(
    val title: String,
    val imageUri: String,
    val resourceName: String,
    val resourceUrl: String,
    val newsLink: String
)