package com.anupdey.app.compose_playground.domain.model.coinpaprika

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)
