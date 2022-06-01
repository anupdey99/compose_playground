package com.anupdey.app.compose_playground.data.local.entities.currencylayer

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.anupdey.app.compose_playground.data.local.converters.DateConverter
import java.util.*

@Entity(tableName = "currency")
@TypeConverters(DateConverter::class)
data class CurrencyEntity(
    @PrimaryKey
    val currency: String,
    val description: String,
    val createdAt: Date = Date()
)
