package com.example.calendarappsimbersoft.presentation.base

import com.example.calendarappsimbersoft.presentation.base.presenter.Presenter


interface MvpViewCallback<View, P : Presenter<View>> {

    fun getPresenter(): P

    fun getMvpView(): View
}