package com.onefootball.presentation.news.presenter

import android.content.res.AssetManager
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.ui.NewsView
import com.onefootball.util.NewsProvider

class NewsPresenterImpl(private val assetManager: AssetManager) : NewsPresenter {
    private var view: NewsView? = null

    override fun setView(view: NewsView) {
        this.view = view
    }

    override fun provideNews() {
        val newsItems = NewsProvider.provideNewsFromFile(assetManager)
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