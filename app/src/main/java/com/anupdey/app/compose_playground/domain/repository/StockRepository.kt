package com.anupdey.app.compose_playground.domain.repository

import com.anupdey.app.compose_playground.domain.model.stock.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.stock.CompanyListing
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo
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

    suspend fun getCompanyListPaging(
        page: Int,
        pageSize: Int
    ): Result<List<CompanyListing>>

}