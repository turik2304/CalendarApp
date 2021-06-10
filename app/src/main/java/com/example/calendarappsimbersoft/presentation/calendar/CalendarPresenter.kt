package com.example.calendarappsimbersoft.presentation.calendar

import com.example.calendarappsimbersoft.domain.Middleware
import com.example.calendarappsimbersoft.presentation.base.presenter.BasePresenter

class CalendarPresenter(
    private val loadEventsMiddleware: Middleware
) : BasePresenter<CalendarView>(CalendarView::class.java) {

    fun loadCalendarEvents() {
        view?.showCalendarEvents(
            loadEventsMiddleware.getCalendarEvents()
        )
    }

    fun loadEventListByDate(dateInMillis: Long) {
        view?.showEventsByDate(
            loadEventsMiddleware.getEventsByDate(dateInMillis)
        )
    }
}