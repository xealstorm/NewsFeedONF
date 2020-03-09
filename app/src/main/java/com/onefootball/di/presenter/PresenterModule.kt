package com.onefootball.di.presenter

import android.content.res.AssetManager
import com.onefootball.di.activity.ActivityScope
import com.onefootball.presentation.news.presenter.NewsPresenter
import com.onefootball.presentation.news.presenter.NewsPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    @ActivityScope
    fun provideNewsPresenter(assetManager: AssetManager): NewsPresenter {
        return NewsPresenterImpl(assetManager)
    }
}