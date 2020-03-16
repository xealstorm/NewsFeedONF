package com.onefootball.di.presenter

import com.onefootball.di.activity.ActivityScope
import com.onefootball.presentation.news.presenter.NewsPresenter
import com.onefootball.presentation.news.presenter.NewsPresenterImpl
import com.onefootball.provider.NewsProvider
import com.onefootball.util.scedulers.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    @ActivityScope
    fun provideNewsPresenter(newsProvider: NewsProvider, schedulerProvider: AppSchedulerProvider): NewsPresenter {
        return NewsPresenterImpl(newsProvider, schedulerProvider)
    }
}