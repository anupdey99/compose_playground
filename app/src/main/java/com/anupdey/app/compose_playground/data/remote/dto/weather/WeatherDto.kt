package com.anupdey.app.compose_playground.data.remote.dto.weather

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("hourly")
    val weatherData: WeatherDataDto
)
