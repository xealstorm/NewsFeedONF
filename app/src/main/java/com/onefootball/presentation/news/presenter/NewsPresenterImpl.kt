package com.onefootball.presentation.news.presenter

import android.util.Log
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.ui.NewsView
import com.onefootball.provider.NewsProvider
import com.onefootball.util.scedulers.SchedulerProvider

class NewsPresenterImpl(
    private val newsProvider: NewsProvider,
    private val schedulerProvider: SchedulerProvider
) : NewsPresenter {
    private var view: NewsView? = null

    override fun setView(view: NewsView) {
        this.view = view
    }

    override fun provideNews() {
        if (view?.hasItems() == false) {
            newsProvider.provideNews()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    val newsItems = it.filter { it.isValid() }
                        .map { news ->
                            NewsItem(
                                news.title!!,
                                news.imageUri!!,
                                news.resourceName!!,
                                news.resourceUrl!!,
                                news.newsLink!!
                            )
                        }
                    view?.updateNewsWithList(newsItems)
                }, {
                    Log.e(
                        NewsPresenterImpl::class.java.toString(),
                        it.message ?: "Unable to provide news"
                    )
                })
        }
    }
}