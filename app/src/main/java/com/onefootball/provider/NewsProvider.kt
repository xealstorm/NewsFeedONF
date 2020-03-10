package com.onefootball.provider

import com.onefootball.model.News

interface NewsProvider {
    fun provideNews(): List<News>
}