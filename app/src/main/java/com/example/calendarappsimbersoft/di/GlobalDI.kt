package com.example.calendarappsimbersoft.di

import android.content.Context
import android.util.Log
import com.example.calendarappsimbersoft.presentation.calendar.CalendarPresenter

class GlobalDI private constructor(
    applicationContext: Context
) {

    val presenter by lazy(::CalendarPresenter)

    companion object {
        lateinit var INSTANCE: GlobalDI

        fun init(applicationContext: Context) {
            INSTANCE = GlobalDI(applicationContext)
        }
    }
}