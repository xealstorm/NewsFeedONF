package com.onefootball.presentation.news.presenter

import com.onefootball.presentation.base.presenter.BasePresenter
import com.onefootball.presentation.news.ui.NewsView

interface NewsPresenter : BasePresenter<NewsView> {
    fun provideNews()
}