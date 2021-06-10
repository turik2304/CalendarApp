package com.example.calendarappsimbersoft.data

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.kotlin.where

class CalendarRepositoryImpl(
    override val realm: Realm
) : CalendarRepository {

    override fun getEvents(): Observable<List<Event>> {
        return realm.where<Event>()
            .findAllAsync()
            .asFlowable()
            .toObservable()
            .observeOn(Schedulers.computation())
            .filter { it.isNotEmpty() }
            .map { it.toList() }
    }
}