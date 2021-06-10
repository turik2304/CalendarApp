package com.example.calendarappsimbersoft.di

import android.content.Context
import com.example.calendarappsimbersoft.data.CalendarRepository
import com.example.calendarappsimbersoft.data.CalendarRepositoryImpl
import com.example.calendarappsimbersoft.domain.LoadEventsMiddleware
import com.example.calendarappsimbersoft.domain.Middleware
import com.example.calendarappsimbersoft.presentation.calendar.CalendarRxPresenter
import com.example.calendarappsimbersoft.utils.DateUtils
import com.example.calendarappsimbersoft.utils.DateUtilsImpl
import io.realm.Realm

class GlobalDI private constructor(
    private val applicationContext: Context
) {
    private val realm: Realm by lazy { Realm.getDefaultInstance() }

    private val repository: CalendarRepository by lazy {
        CalendarRepositoryImpl(realm)
    }

    private val dateUtils: DateUtils by lazy { DateUtilsImpl() }

    private val loadEventsMiddleware: Middleware by lazy {
        LoadEventsMiddleware(
            repository,
            dateUtils
        )
    }

    val presenter by lazy {
        CalendarRxPresenter(loadEventsMiddleware)
    }

    companion object {
        lateinit var INSTANCE: GlobalDI

        fun init(applicationContext: Context) {
            INSTANCE = GlobalDI(applicationContext)
        }
    }
}