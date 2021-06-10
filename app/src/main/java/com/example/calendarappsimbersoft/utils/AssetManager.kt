package com.example.calendarappsimbersoft.utils

import android.content.Context

interface AssetManager {
    fun getJsonDataFromAsset(context: Context, fileName: String): String?
}