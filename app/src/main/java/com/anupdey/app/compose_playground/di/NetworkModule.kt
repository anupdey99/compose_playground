package com.anupdey.app.compose_playground.di

import com.anupdey.app.compose_playground.BuildConfig
import com.anupdey.app.compose_playground.data.remote.CoinPaprikaAPI
import com.anupdey.app.compose_playground.data.remote.CurrencyLayerAPI
import com.anupdey.app.compose_playground.data.remote.StockAPI
import com.anupdey.app.compose_playground.data.remote.WeatherApi
import com.anupdey.app.compose_playground.util.AppConstant
import com.anupdey.app.compose_playground.util.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideStockAPI(client: OkHttpClient): StockAPI {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_ALPHAVANTAGE)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(StockAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinPaprikaAPI(client: OkHttpClient): CoinPaprikaAPI {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_COINPAPRIKA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CoinPaprikaAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyLayerAPI(client: OkHttpClient): CurrencyLayerAPI {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_CURRENCYLAYER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CurrencyLayerAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_OPEN_METEO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherApi::class.java)
    }

}