package com.anupdey.app.compose_playground.util

import com.anupdey.app.compose_playground.data.local.entities.currencylayer.CurrencyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

inline fun <ResultType, RequestType> networkBoundResourceFlow(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    coroutineDispatcher: CoroutineDispatcher
) = flow<Resource<ResultType>> {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            val fetchData = fetch()
            saveFetchResult(fetchData)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { Resource.Error(ApiError(ErrorType.FAILURE, "Something went wrong"), it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}.onStart {
    emit(Resource.Loading(null))
}.catch {
    emit(Resource.Error(ApiError(ErrorType.FAILURE, "An error occurred while fetching data!"), null))
}.flowOn(coroutineDispatcher)