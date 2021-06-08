package com.example.calendarappsimbersoft.presentation.calendar

import com.example.calendarappsimbersoft.domain.LoadEventsMiddleware
import com.example.calendarappsimbersoft.presentation.base.presenter.BasePresenter

class CalendarPresenter(
    private val loadEventsMiddleware: LoadEventsMiddleware
) : BasePresenter<CalendarView>(CalendarView::class.java) {

    fun loadCalendarEvents() {
        view?.showCalendarEvents(
            loadEventsMiddleware.getEvents()
        )
    }

    fun loadEventListByDate(dateInMillis: Long) {
        view?.showEventsByDate(
            loadEventsMiddleware.getEventsByDate(dateInMillis)
        )
    }
}