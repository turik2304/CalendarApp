package com.example.calendarappsimbersoft.presentation.base.presenter

import android.view.ViewStub

abstract class BasePresenter<View> protected constructor(
    viewClass: Class<View>
) : Presenter<View> {

    var view: View? = null

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView(isFinishing: Boolean) {
        if (isFinishing) {
            view = null
        }
    }
}