package com.example.calendarappsimbersoft.presentation.calendar

import android.util.Log
import com.example.calendarappsimbersoft.domain.Middleware
import com.example.calendarappsimbersoft.presentation.base.presenter.RxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm

class CalendarRxPresenter(
    private val loadEventsMiddleware: Middleware
) : RxPresenter<CalendarView>(CalendarView::class.java) {

    override fun detachView(isFinishing: Boolean) {
        super.detachView(isFinishing)
        Realm.getDefaultInstance()
            .close()
    }

    fun loadCalendarEvents() {
        view?.showLoading()
        loadEventsMiddleware.getCalendarEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ calendarEvents ->
                view?.showCalendarEvents(calendarEvents)
            }, { error -> Log.d("xxx", "Error loading calendar events $error") })
            .disposeOnFinish()
    }

    fun loadEventListByDate(dateInMillis: Long) {
        loadEventsMiddleware.getEventsByDate(dateInMillis)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ events ->
                if (events.isEmpty()) {
                    view?.showEmptyEvent()
                } else {
                    view?.showEventsByDate(events)
                }
            }, { error -> Log.d("xxx", "Error loading event by date $error") })
            .disposeOnFinish()
    }
}