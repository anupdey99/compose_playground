package com.anupdey.app.compose_playground.presentation.coinpaprika.coin_list

import com.anupdey.app.compose_playground.domain.model.coinpaprika.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = ""
)