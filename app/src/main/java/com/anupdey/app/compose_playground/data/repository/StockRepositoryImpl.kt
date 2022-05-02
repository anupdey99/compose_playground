package com.anupdey.app.compose_playground.data.repository

import android.util.Log
import com.anupdey.app.compose_playground.data.csv.CSVParser
import com.anupdey.app.compose_playground.data.local.StockDatabase
import com.anupdey.app.compose_playground.data.mapper.toCompanyInfo
import com.anupdey.app.compose_playground.data.mapper.toCompanyListing
import com.anupdey.app.compose_playground.data.mapper.toCompanyListingEntity
import com.anupdey.app.compose_playground.data.remote.APIInterface
import com.anupdey.app.compose_playground.domain.model.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.CompanyListing
import com.anupdey.app.compose_playground.domain.model.IntradayInfo
import com.anupdey.app.compose_playground.domain.repository.StockRepository
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
    private val api: APIInterface,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(fetchFromRemote: Boolean, query: String): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
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
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Log.d("repoDebug", "$results")
            Resource.Success(results)
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
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