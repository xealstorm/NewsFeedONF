package com.onefootball.di.news

import com.onefootball.presentation.news.ui.NewsAdapter
import dagger.Module
import dagger.Provides

@Module
class NewsModule {
    @NewsScope
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}