package com.anupdey.app.compose_playground.data.repository

import com.anupdey.app.compose_playground.data.mapper.toCoin
import com.anupdey.app.compose_playground.data.mapper.toCoinDetail
import com.anupdey.app.compose_playground.data.remote.CoinPaprikaAPI
import com.anupdey.app.compose_playground.domain.model.coinpaprika.Coin
import com.anupdey.app.compose_playground.domain.model.coinpaprika.CoinDetail
import com.anupdey.app.compose_playground.domain.repository.CoinRepository
import com.anupdey.app.compose_playground.util.ApiError
import com.anupdey.app.compose_playground.util.ErrorType
import com.anupdey.app.compose_playground.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaAPI
) : CoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val list = api.getCoins()
            val coinList = list.map { it.toCoin() }
            emit(Resource.Success(coinList))
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

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val detailsDto = api.getCoinById(coinId)
            val details = detailsDto.toCoinDetail()
            emit(Resource.Success(details))
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