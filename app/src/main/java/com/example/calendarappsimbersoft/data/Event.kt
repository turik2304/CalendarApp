package com.example.calendarappsimbersoft.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Event(
    var name: String = "",
    var description: String = "",
    var startDateInSeconds: Long = 0,
    var endDateInSeconds: Long = 0,
    @PrimaryKey
    var id: Int = 0
) : RealmObject()