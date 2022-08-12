package com.anupdey.app.compose_playground.util.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun connectionObserve(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}