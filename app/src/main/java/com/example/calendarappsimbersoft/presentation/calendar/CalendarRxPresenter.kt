package com.example.calendarappsimbersoft.presentation.calendar

import android.util.Log
import com.example.calendarappsimbersoft.domain.Middleware
import com.example.calendarappsimbersoft.presentation.base.presenter.RxPresenter
import com.example.calendarappsimbersoft.presentation.calendar.recycler.items.CreateEventDayUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm

class CalendarRxPresenter(
    private val eventsMiddleware: Middleware
) : RxPresenter<CalendarView>(CalendarView::class.java) {

    override fun detachView(isFinishing: Boolean) {
        super.detachView(isFinishing)
        Realm.getDefaultInstance()
            .close()
    }

    fun loadCalendarEvents() {
        view?.showLoading()
        eventsMiddleware.getCalendarEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ calendarEvents ->
                view?.showCalendarEvents(calendarEvents)
            }, { error -> Log.d("xxx", "Error loading calendar events $error") })
            .disposeOnFinish()
    }

    fun loadEventListByDate(dateInMillis: Long) {
        eventsMiddleware.getEventsByDate(dateInMillis)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ events ->
                val createEventItem = listOf(
                    CreateEventDayUI(
                        createEventDateInMillis = dateInMillis,
                        uid = 0
                    )
                )
                view?.showEventsByDate(createEventItem + events)
            }, { error -> Log.d("xxx", "Error loading event by date $error") })
            .disposeOnFinish()
    }

    fun addEvent(
        name: String,
        description: String,
        startDateInMillis: Long,
        endDateInMillis: Long,
    ) {
        eventsMiddleware.addEvent(
            name = name,
            description = description,
            startDateInMillis = startDateInMillis,
            endDateInMillis = endDateInMillis
        )
    }
}