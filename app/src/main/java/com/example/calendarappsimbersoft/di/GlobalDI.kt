package com.example.calendarappsimbersoft.di

import android.content.Context
import com.example.calendarappsimbersoft.domain.LoadEventsMiddleware
import com.example.calendarappsimbersoft.presentation.calendar.CalendarPresenter

class GlobalDI private constructor(
    applicationContext: Context
) {

    private val loadEventsMiddleware by lazy { LoadEventsMiddleware() }

    val presenter by lazy {
        CalendarPresenter(loadEventsMiddleware)
    }

    companion object {
        lateinit var INSTANCE: GlobalDI

        fun init(applicationContext: Context) {
            INSTANCE = GlobalDI(applicationContext)
        }
    }
}