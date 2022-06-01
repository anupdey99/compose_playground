package com.anupdey.app.compose_playground.domain.repository

import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyListing
import com.anupdey.app.compose_playground.util.Resource
import kotlinx.coroutines.flow.Flow


interface CurrencyLayerRepository {

    fun currencyList(): Flow<Resource<List<CurrencyListing>>>

    suspend fun fetchChargeData(source: String): Flow<Resource<Map<String, Double>>>
}