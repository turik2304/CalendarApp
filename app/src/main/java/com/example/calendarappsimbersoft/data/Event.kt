package com.example.calendarappsimbersoft.data

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Event(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("date_start")
    var startDate: Long = 0,
    @SerializedName("date_finish")
    var endDate: Long = 0,
    @SerializedName("id")
    @PrimaryKey
    var id: Int = 0,
) : RealmObject()