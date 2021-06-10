package com.example.calendarappsimbersoft.data

import android.content.Context
import android.util.Log
import com.example.calendarappsimbersoft.utils.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CalendarRepositoryImpl(
    private val applicationContext: Context,
    private val assetManager: AssetManager
) : CalendarRepository {

    private val gson = Gson()

    override fun getEvents(): List<Event> {
        val jsonFileString = assetManager.getJsonDataFromAsset(applicationContext, "events.json")
        Log.d("xxx", "Events data: $jsonFileString")
        val eventsListType = object : TypeToken<List<Event>>() {}.type
        val events: List<Event> = gson.fromJson(jsonFileString, eventsListType)
        Log.d("xxx", "Events models: $events")
        return events
    }
}