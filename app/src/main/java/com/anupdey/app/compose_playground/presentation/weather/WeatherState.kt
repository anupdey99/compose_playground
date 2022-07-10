package com.anupdey.app.compose_playground.presentation.weather

import com.anupdey.app.compose_playground.domain.model.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)