package com.anupdey.app.compose_playground.domain.repository

import com.anupdey.app.compose_playground.domain.model.coinpaprika.Coin
import com.anupdey.app.compose_playground.domain.model.coinpaprika.CoinDetail
import com.anupdey.app.compose_playground.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>>
}