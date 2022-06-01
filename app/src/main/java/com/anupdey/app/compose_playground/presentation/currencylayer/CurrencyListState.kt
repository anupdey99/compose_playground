package com.anupdey.app.compose_playground.presentation.currencylayer

import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyConversionData
import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyListing

data class CurrencyListState(
    val isLoading: Boolean = false,
    val list: List<CurrencyListing> = emptyList(),
    val error: String? = "",
    val conversionList: List<CurrencyConversionData> = emptyList(),
    val amount: Double = 0.0,
    val source: String = ""
)
