package com.anupdey.app.compose_playground.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

inline fun <T> networkBoundResource(
    crossinline query: () -> Flow<T>,
    crossinline fetch: suspend () -> ApiResponse<T>,
    crossinline saveFetchResult: suspend (data: T) -> Unit,
    crossinline shouldFetch: (data: T) -> Boolean = { true },
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    //coroutineDispatcher: CoroutineDispatcher
) = flow<Resource<T>> {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        val fetchedData = fetch()
        when (fetchedData) {
            is ApiResponse.Error -> {
                query().map { Resource.Error(fetchedData.error!!, fetchedData) }
            }
            is ApiResponse.Success -> {
                fetchedData.data?.let {
                    saveFetchResult(it)
                }
                query().map { Resource.Success(fetchedData.data) }
            }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    //emitAll(flow)
}.onStart {
    emit(Resource.Loading(null))
}.catch {
    emit(Resource.Error(ApiError(ErrorType.FAILURE, "An error occurred while fetching data!"), null))
}//.flowOn(coroutineDispatcher)