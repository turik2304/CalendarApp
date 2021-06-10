package com.example.calendarappsimbersoft.utils

import android.content.Context
import android.widget.Toast
import java.io.IOException

class AssetManagerImpl : AssetManager {

    override fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            Toast.makeText(context, "Error open Events data!", Toast.LENGTH_SHORT).show()
            return null
        }
        return jsonString
    }
}