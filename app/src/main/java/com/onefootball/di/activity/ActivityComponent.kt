package com.onefootball.di.activity

import com.onefootball.di.app.AppComponent
import com.onefootball.di.news.NewsComponent
import com.onefootball.di.news.NewsModule
import com.onefootball.di.presenter.PresenterModule
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, PresenterModule::class]
)
interface ActivityComponent {
    fun plus(ratesModule: NewsModule): NewsComponent
}