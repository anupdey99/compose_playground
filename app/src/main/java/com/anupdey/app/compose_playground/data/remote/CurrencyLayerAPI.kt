package com.anupdey.app.compose_playground.data.remote

import com.anupdey.app.compose_playground.BuildConfig
import com.anupdey.app.compose_playground.data.remote.dto.currencylayer.ChargeResponseDto
import com.anupdey.app.compose_playground.data.remote.dto.currencylayer.CurrencyResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyLayerAPI {

    @Headers("apikey:${BuildConfig.CURRENCYLAYER_API_KEY}")
    @GET("currency_data/list")
    suspend fun currencyList(): CurrencyResponseDto

    @Headers("apikey:${BuildConfig.CURRENCYLAYER_API_KEY}")
    @GET("currency_data/live")
    suspend fun fetchChargeData(
        @Query("source") source: String
    ): ChargeResponseDto
}