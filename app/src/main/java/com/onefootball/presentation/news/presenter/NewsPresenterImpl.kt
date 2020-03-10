package com.onefootball.presentation.news.presenter

import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.ui.NewsView
import com.onefootball.provider.NewsProvider

class NewsPresenterImpl(private val newsProvider: NewsProvider) : NewsPresenter {
    private var view: NewsView? = null

    override fun setView(view: NewsView) {
        this.view = view
    }

    override fun provideNews() {
        if (view?.hasItems() == false) {
            val newsItems = newsProvider.provideNews()
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
}