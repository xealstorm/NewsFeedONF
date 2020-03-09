package com.onefootball.presentation.news.presenter

import com.onefootball.App
import com.onefootball.presentation.base.presenter.BasePresenter
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.ui.NewsView
import com.onefootball.util.NewsProvider

class NewsPresenter : BasePresenter<NewsView> {
    private var view: NewsView? = null

    override fun setView(view: NewsView) {
        this.view = view
    }

    fun provideNews() {
        val newsItems = NewsProvider.provideNewsFromFile(App.context)
            .filter { it.isValid() }
            .map {
                NewsItem(
                    it.title!!,
                    it.imageUri!!,
                    it.resourceName!!,
                    it.resourceUrl!!,
                    it.newsLink!!
                )
            }
        view?.updateNewsWithList(newsItems)
    }
}