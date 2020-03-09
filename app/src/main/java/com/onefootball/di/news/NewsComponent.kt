package com.onefootball.di.news

import com.onefootball.presentation.news.ui.NewsActivity
import dagger.Subcomponent

@NewsScope
@Subcomponent(modules = [NewsModule::class])
interface NewsComponent {
    fun injectTo(activity: NewsActivity)
}