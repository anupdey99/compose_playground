package com.anupdey.app.compose_playground.util

import java.util.concurrent.TimeUnit

object AppConstant {
    //Database
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "compose_playground.db"
    //Base urls
    const val BASE_URL_ALPHAVANTAGE = "https://www.alphavantage.co/"
    const val BASE_URL_COINPAPRIKA = "https://api.coinpaprika.com/"
    const val BASE_URL_CURRENCYLAYER = "https://api.apilayer.com/"
    const val BASE_URL_OPEN_METEO = "https://api.open-meteo.com/"
    //cache time
    val CACHE_TIME_IN_MILLIS = TimeUnit.MINUTES.toMillis(30) //30 minutes
}