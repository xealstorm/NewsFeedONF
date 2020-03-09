package com.onefootball.presentation.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onefootball.App
import com.onefootball.di.activity.ActivityComponent
import com.onefootball.di.activity.DaggerActivityComponent

abstract class BaseActivity: AppCompatActivity() {
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = createActivityComponent()
        doInjections(activityComponent)
    }

    protected abstract fun doInjections(activityComponent: ActivityComponent?)

    private fun createActivityComponent(): ActivityComponent {
        val app = applicationContext as App
        return DaggerActivityComponent
            .builder()
            .appComponent(app.appComponent)
            .build()
    }
}