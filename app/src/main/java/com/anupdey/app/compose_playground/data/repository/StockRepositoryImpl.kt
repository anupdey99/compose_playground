package com.anupdey.app.compose_playground.data.repository

import android.util.Log
import com.anupdey.app.compose_playground.data.csv.CSVParser
import com.anupdey.app.compose_playground.data.local.AppDatabase
import com.anupdey.app.compose_playground.data.mapper.toCompanyInfo
import com.anupdey.app.compose_playground.data.mapper.toCompanyListing
import com.anupdey.app.compose_playground.data.mapper.toCompanyListingEntity
import com.anupdey.app.compose_playground.data.remote.StockAPI
import com.anupdey.app.compose_playground.domain.model.stock.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.stock.CompanyListing
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo
import com.anupdey.app.compose_playground.domain.repository.StockRepository
import com.anupdey.app.compose_playground.util.ApiError
import com.anupdey.app.compose_playground.util.ErrorType
import com.anupdey.app.compose_playground.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val stockApi: StockAPI,
    private val appDatabase: AppDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
): StockRepository {

    private val dao = appDatabase.stockDao

    override suspend fun getCompanyListings(fetchFromRemote: Boolean, query: String): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading())
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading())
                return@flow
            }
            val remoteListings = try {
                val response = stockApi.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(ApiError(ErrorType.NETWORK, "Couldn't reach server. Check your internet connection")))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(ApiError(ErrorType.FAILURE, e.localizedMessage ?: "An unexpected error occurred")))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading())
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = stockApi.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Log.d("repoDebug", "$results")
            Resource.Success(results)
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(ApiError(ErrorType.NETWORK, "Couldn't reach server. Check your internet connection"))
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(ApiError(ErrorType.FAILURE, e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = stockApi.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(ApiError(ErrorType.NETWORK, "Couldn't reach server. Check your internet connection"))
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(ApiError(ErrorType.FAILURE, e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getCompanyListPaging(page: Int, pageSize: Int): Result<List<CompanyListing>> {
        return try {
            delay(2000L)
            val startingIndex = page * pageSize
            val lastIndex = startingIndex + pageSize
            if (lastIndex <= pagingCompanyList.size) {
                val list = pagingCompanyList.slice(startingIndex until lastIndex)
                Result.success(list)
            } else {
                Result.success(emptyList())
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Result.failure(Throwable(e.message))
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.failure(Throwable(e.message))
        }
    }

    private val pagingCompanyList = (1..100).map {
        CompanyListing(
            "Company $it",
            "S$it",
            "ex$it"
        )
    }

}