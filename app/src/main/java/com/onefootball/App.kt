package com.onefootball

import android.app.Application
import android.content.Context
import com.onefootball.di.app.AppComponent
import com.onefootball.di.app.AppModule
import com.onefootball.di.app.DaggerAppComponent

class App : Application() {

    var appComponent: AppComponent? = null
    private set

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var context: Context
            private set
    }
}
