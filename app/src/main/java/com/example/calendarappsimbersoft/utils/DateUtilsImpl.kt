package com.example.calendarappsimbersoft.utils

import java.text.SimpleDateFormat

class DateUtilsImpl : DateUtils {

    private val formatterForComparisons = SimpleDateFormat("dd MM yyyy")
    private val timeRangeFormatter = SimpleDateFormat("HH:mm")

    override fun areDatesIsSame(firstDateInMillis: Long, secondDateInMillis: Long): Boolean {
        return formatterForComparisons.format(firstDateInMillis) == formatterForComparisons.format(
            secondDateInMillis
        )
    }

    override fun formatToTimeRange(startDateInMillis: Long, endDateInMillis: Long): String {
        return timeRangeFormatter.format(startDateInMillis) + " - " + timeRangeFormatter.format(
            endDateInMillis
        )
    }
}