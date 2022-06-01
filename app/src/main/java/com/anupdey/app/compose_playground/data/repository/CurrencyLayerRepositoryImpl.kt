package com.anupdey.app.compose_playground.data.repository

import com.anupdey.app.compose_playground.data.local.AppDatabase
import com.anupdey.app.compose_playground.data.mapper.mapToCurrencyEntityList
import com.anupdey.app.compose_playground.data.mapper.mapToCurrencyList
import com.anupdey.app.compose_playground.data.remote.CurrencyLayerAPI
import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyListing
import com.anupdey.app.compose_playground.domain.repository.CurrencyLayerRepository
import com.anupdey.app.compose_playground.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyLayerRepositoryImpl @Inject constructor(
    private val apiInterface: CurrencyLayerAPI,
    private val appDatabase: AppDatabase
): CurrencyLayerRepository {

    //database dao
    private val currencyDao = appDatabase.currencyDao

    override fun currencyList(): Flow<Resource<List<CurrencyListing>>> {

        return networkBoundResourceFlow(
            query = {
                currencyDao.fetchAllFlow().map {
                    it.mapToCurrencyList()
                }
            }, fetch = {
                //apiInterface.currencyList()
                apiCall { apiInterface.currencyList() }

            },
            saveFetchResult = { response ->
                //val currencyList = response.mapToCurrencyEntityList()
                //currencyDao.deleteTableAndInsert(currencyList)

                when(response) {
                    is ApiResponse.Error -> {}
                    is ApiResponse.Success -> {
                        if (response.data?.success == true) {
                            //val currencyList = response.data.mapToCurrencyList()
                            val currencyList = response.data.mapToCurrencyEntityList()
                            currencyDao.deleteTableAndInsert(currencyList)
                            ApiResponse.Success(currencyList)
                        } else {
                            ApiResponse.Error(ApiError(ErrorType.FAILURE, "Something went wrong"))
                        }
                    }
                }
            },
            shouldFetch = {
                it.isNullOrEmpty() || checkCacheValidity(it.first().createdAt)
            },
            coroutineDispatcher = Dispatchers.IO
        )

        /*return object : NetworkResourceBounce<List<CurrencyListing>>() {

            override suspend fun query(): List<CurrencyListing> {
                return currencyDao.fetchAllFlow().map {
                    it.mapToCurrencyList()
                }.first()
            }

            override fun queryObservable(): Flow<List<CurrencyListing>> {
                return currencyDao.fetchAllFlow().map {
                    it.mapToCurrencyList()
                }
            }

            override suspend fun fetch(): ApiResponse<List<CurrencyListing>> {
                return when(val response = apiCall { apiInterface.currencyList() }) {
                    is ApiResponse.Error -> ApiResponse.Error(response.error!!)
                    is ApiResponse.Success -> {
                        if (response.data?.success == true) {
                            val currencyList = response.data.mapToCurrencyList()
                            ApiResponse.Success(currencyList)
                        } else {
                            ApiResponse.Error(ApiError(ErrorType.FAILURE, "Something went wrong"))
                        }
                    }
                }
            }

            override suspend fun saveFetchResult(data: List<CurrencyListing>) {
                val currencyList = data.mapToCurrencyEntityList()
                currencyDao.deleteTableAndInsert(currencyList)
            }

            override fun shouldFetch(data: List<CurrencyListing>?): Boolean {
                return data.isNullOrEmpty() || checkCacheValidity(data.first().createdAt)
            }

        }.asFlow()*/

        /*when(val response = apiCall {
            apiInterface.currencyList()
        }) {
            is ApiResponse.Error -> response
            is ApiResponse.Success -> {
                if (response.data.success) {
                    val currencyList = response.data.mapToCurrencyList()
                } else {
                    ApiResponse.Error(ApiError(ErrorType.FAILURE, "Something went wrong"))
                }
            }
        }*/

    }

    override suspend fun fetchChargeData(source: String): Flow<Resource<Map<String, Double>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiInterface.fetchChargeData(source)
                emit(Resource.Success(response.quotes))
                emit(Resource.Loading())
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Loading())
                emit(Resource.Error(ApiError(ErrorType.FAILURE, e.localizedMessage ?: "An unexpected error occurred")))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Loading())
                emit(Resource.Error(ApiError(ErrorType.NETWORK, "Couldn't reach server. Check your internet connection")))
            }
        }
    }

    private fun checkCacheValidity(date: Date): Boolean {
        val elapsedTime = Date().time - date.time
        return elapsedTime >= AppConstant.CACHE_TIME_IN_MILLIS
    }

}