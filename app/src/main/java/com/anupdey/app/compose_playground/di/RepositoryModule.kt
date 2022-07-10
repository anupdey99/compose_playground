package com.anupdey.app.compose_playground.di

import com.anupdey.app.compose_playground.data.csv.CSVParser
import com.anupdey.app.compose_playground.data.csv.CompanyListingsParser
import com.anupdey.app.compose_playground.data.csv.IntradayInfoParser
import com.anupdey.app.compose_playground.data.repository.CoinRepositoryImpl
import com.anupdey.app.compose_playground.data.repository.CurrencyLayerRepositoryImpl
import com.anupdey.app.compose_playground.data.repository.StockRepositoryImpl
import com.anupdey.app.compose_playground.data.repository.WeatherRepositoryImpl
import com.anupdey.app.compose_playground.domain.model.stock.CompanyListing
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo
import com.anupdey.app.compose_playground.domain.repository.CoinRepository
import com.anupdey.app.compose_playground.domain.repository.CurrencyLayerRepository
import com.anupdey.app.compose_playground.domain.repository.StockRepository
import com.anupdey.app.compose_playground.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntraDayInfoParser(
        intraDayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

    @Binds
    @Singleton
    abstract fun bindCoinRepository(
        repository: CoinRepositoryImpl
    ): CoinRepository

    @Binds
    @Singleton
    abstract fun bindCurrencyLayerRepository(
        repository: CurrencyLayerRepositoryImpl
    ): CurrencyLayerRepository

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}