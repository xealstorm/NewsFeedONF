package com.onefootball.di.app

import android.content.res.AssetManager
import com.onefootball.App
import com.onefootball.util.scedulers.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideAssetManager(): AssetManager {
        return app.assets
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()
}