package com.anupdey.app.compose_playground.presentation.currencylayer

sealed class UIEvent {
    data class AmountChanged(val amount: Double): UIEvent()
    data class CurrencyChanged(val currency: String): UIEvent()
    object Submit: UIEvent()
}
