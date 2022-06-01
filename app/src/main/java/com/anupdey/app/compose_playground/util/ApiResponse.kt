package com.anupdey.app.compose_playground.util

sealed class ApiResponse<T>(
    val data: T? = null,
    val error: ApiError? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(error: ApiError, data: T? = null) : ApiResponse<T>(data, error)
}
