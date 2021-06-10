package com.example.calendarappsimbersoft.presentation.base.presenter

interface Presenter<View> {
    fun attachView(view: View)
    fun detachView(isFinishing: Boolean)
}