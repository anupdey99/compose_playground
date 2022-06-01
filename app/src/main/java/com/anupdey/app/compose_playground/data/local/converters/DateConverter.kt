package com.anupdey.app.compose_playground.data.local.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timeInMillis: Long): Date = Date(timeInMillis)

    @TypeConverter
    fun fromDate(date: Date): Long = date.time
}