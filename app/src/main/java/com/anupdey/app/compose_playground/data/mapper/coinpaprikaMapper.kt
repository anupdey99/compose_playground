package com.anupdey.app.compose_playground.data.mapper

import com.anupdey.app.compose_playground.data.remote.dto.coinpaprika.CoinDetailDto
import com.anupdey.app.compose_playground.data.remote.dto.coinpaprika.CoinDto
import com.anupdey.app.compose_playground.domain.model.coinpaprika.Coin
import com.anupdey.app.compose_playground.domain.model.coinpaprika.CoinDetail

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id ?: "",
        isActive = isActive,
        name = name ?: "",
        rank = rank,
        symbol = symbol ?: ""
    )
}

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id ?: "",
        name = name ?: "",
        description = description ?: "",
        symbol = symbol ?: "",
        rank = rank,
        isActive = isActive,
        tags = (tags?.map { it.name ?: "" }) ?: emptyList(),
        team = team ?: emptyList()
    )
}