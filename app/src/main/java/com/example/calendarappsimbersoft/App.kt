package com.example.calendarappsimbersoft

import android.app.Application
import com.example.calendarappsimbersoft.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init(this)
    }
}