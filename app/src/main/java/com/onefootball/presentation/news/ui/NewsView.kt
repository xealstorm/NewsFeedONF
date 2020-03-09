package com.onefootball.presentation.news.ui

import com.onefootball.presentation.base.ui.BaseView
import com.onefootball.presentation.news.model.NewsItem

interface NewsView : BaseView {
    fun updateNewsWithList(newsList: List<NewsItem>)
}