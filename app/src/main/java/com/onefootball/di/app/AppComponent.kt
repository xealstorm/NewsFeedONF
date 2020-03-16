package com.onefootball.di.app

import android.content.res.AssetManager
import com.onefootball.util.scedulers.AppSchedulerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun provideAssetManager(): AssetManager

    fun schedulerProvider(): AppSchedulerProvider
}