package com.anupdey.app.compose_playground.domain.repository

import com.anupdey.app.compose_playground.domain.model.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.CompanyListing
import com.anupdey.app.compose_playground.domain.model.IntradayInfo
import com.anupdey.app.compose_playground.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}