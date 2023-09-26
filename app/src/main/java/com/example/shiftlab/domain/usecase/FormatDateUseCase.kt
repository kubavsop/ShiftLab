package com.example.shiftlab.domain.usecase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormatDateUseCase {
    private companion object {
        const val DATE_PATTERN = "dd.MM.yyyy"
    }

    operator fun invoke(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}