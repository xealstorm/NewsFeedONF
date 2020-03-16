package com.onefootball.provider

import com.onefootball.model.News
import io.reactivex.Observable

interface NewsProvider {
    fun provideNews(): Observable<List<News>>
}