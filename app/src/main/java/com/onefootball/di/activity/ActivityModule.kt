package com.onefootball.di.activity

import android.content.res.AssetManager
import com.onefootball.provider.NewsProvider
import com.onefootball.provider.NewsProviderImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule() {
    @ActivityScope
    @Provides
    fun provideNewsProvider(assetManager: AssetManager): NewsProvider {
        return NewsProviderImpl(assetManager)
    }
}