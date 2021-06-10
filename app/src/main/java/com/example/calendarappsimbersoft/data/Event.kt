package com.example.calendarappsimbersoft.data

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date_start")
    val startDate: Long,
    @SerializedName("date_finish")
    val endDate: Long,
    @SerializedName("id")
    val id: Int,
)