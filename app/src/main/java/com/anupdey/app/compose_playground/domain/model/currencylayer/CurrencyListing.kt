package com.anupdey.app.compose_playground.domain.model.currencylayer

import java.util.*

data class CurrencyListing(
    val currency: String,
    val description: String,
    val createdAt: Date
)
