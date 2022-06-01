package com.anupdey.app.compose_playground.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkResourceBounce<T> {

    /*fun asLiveData() = liveData<Resource<T>> {
        emit(Resource.Loading())
        if (shouldFetch(query())) {
            val disposable = emitSource(queryObservable().map { Resource.Loading(it) })
            val fetchedData = fetch()
            disposable.dispose()
            when (fetchedData) {
                is ApiResponse.Error -> {
                    emitSource(queryObservable().map { Resource.Error(fetchedData.error) })
                }
                is ApiResponse.Success -> {
                    saveFetchResult(fetchedData.data!!)
                    emitSource(queryObservable().map { Resource.Success(it) })
                }
            }
        } else {
            emitSource(queryObservable().map { Resource.Success(it) })
        }
    }*/

    fun asFlow() = flow<Resource<T>> {
        emit(Resource.Loading())
        if (shouldFetch(query())) {
            queryObservable().map {
                emit(Resource.Loading(it))
            }
            val fetchedData = fetch()
            when (fetchedData) {
                is ApiResponse.Error -> {
                    queryObservable().map {
                        emit(Resource.Error(fetchedData.error ?: ApiError(ErrorType.FAILURE, "Something went wrong")))
                    }
                }
                is ApiResponse.Success -> {
                    saveFetchResult(fetchedData.data!!)
                    queryObservable().map {
                        emit(Resource.Success(it))
                    }
                }
            }
        } else {
            queryObservable().map {
                emit(Resource.Success(it))
            }
        }
    }

    abstract suspend fun query(): T
    abstract fun queryObservable(): Flow<T>
    abstract suspend fun fetch(): ApiResponse<T>
    abstract suspend fun saveFetchResult(data: T)
    abstract fun shouldFetch(data: T?): Boolean
}