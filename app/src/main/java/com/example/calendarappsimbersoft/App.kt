package com.example.calendarappsimbersoft

import android.app.Application
import com.example.calendarappsimbersoft.di.GlobalDI
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name("CalendarAppDatabase")
            .build()
        Realm.setDefaultConfiguration(config)
    }
}