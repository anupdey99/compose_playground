package com.anupdey.app.compose_playground.presentation.coinpaprika.coin_detail

import com.anupdey.app.compose_playground.domain.model.coinpaprika.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String? = ""
)