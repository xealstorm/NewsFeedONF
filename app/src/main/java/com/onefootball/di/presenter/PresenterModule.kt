package com.onefootball.di.presenter

import com.onefootball.di.activity.ActivityScope
import com.onefootball.presentation.news.presenter.NewsPresenter
import com.onefootball.presentation.news.presenter.NewsPresenterImpl
import com.onefootball.provider.NewsProvider
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    @ActivityScope
    fun provideNewsPresenter(newsProvider: NewsProvider): NewsPresenter {
        return NewsPresenterImpl(newsProvider)
    }
}