package com.example.calendarappsimbersoft.data

import io.reactivex.Observable
import io.realm.Realm

interface CalendarRepository {
    val realm: Realm
    fun getEvents(): Observable<List<Event>>
}