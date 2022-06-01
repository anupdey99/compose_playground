package com.anupdey.app.compose_playground.data.mapper

import com.anupdey.app.compose_playground.data.local.entities.currencylayer.CurrencyEntity
import com.anupdey.app.compose_playground.data.remote.dto.currencylayer.CurrencyResponseDto
import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyConversionData
import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyListing
import java.util.*

fun CurrencyResponseDto.mapToCurrencyList(): List<CurrencyListing> {
    return this.currencies.map {
        CurrencyListing(it.key, it.value, Date())
    }
}

fun CurrencyResponseDto.mapToCurrencyEntityList(): List<CurrencyEntity> {
    return this.currencies.map {
        CurrencyEntity(it.key, it.value)
    }
}

fun List<CurrencyEntity>.mapToCurrencyList(): List<CurrencyListing> {
    return this.map {
        CurrencyListing(it.currency, it.description, it.createdAt)
    }
}

fun List<CurrencyListing>.mapToCurrencyEntityList(): List<CurrencyEntity> {
    return this.map {
        CurrencyEntity(it.currency, it.description)
    }
}

fun CurrencyEntity.mapToCurrencyList(): CurrencyListing {
    return CurrencyListing(this.currency, this.description, this.createdAt)
}

fun mapToCurrencyConversionData(map: Map<String, Double>, amount: Double): List<CurrencyConversionData> {
    val list: MutableList<CurrencyConversionData> = mutableListOf()
    map.forEach { (key, value) ->
        list.add(CurrencyConversionData(key.removePrefix("USD"), value * amount))
    }
    return list
}

