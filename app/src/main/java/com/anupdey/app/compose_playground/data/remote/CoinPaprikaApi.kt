package com.anupdey.app.compose_playground.data.remote

import com.anupdey.app.compose_playground.data.remote.dto.coinpaprika.CoinDetailDto
import com.anupdey.app.compose_playground.data.remote.dto.coinpaprika.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}