package com.onefootball.presentation.base.presenter

import com.onefootball.presentation.base.ui.BaseView

interface BasePresenter<T : BaseView> {
    fun setView(view: T)
}