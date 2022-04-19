package com.anupdey.app.compose_playground.data.remote

import com.anupdey.app.compose_playground.BuildConfig
import com.anupdey.app.compose_playground.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("query")
    suspend fun getListings(
        @Query("function") function: String = "LISTING_STATUS",
        @Query("apikey") apikey: String = BuildConfig.API_KEY
    ): ResponseBody

    @GET("query")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("function") function: String = "TIME_SERIES_INTRADAY",
        @Query("interval") interval: String = "60min",
        @Query("datatype") datatype: String = "csv",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): ResponseBody

    @GET("query")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("function") function: String = "OVERVIEW",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): CompanyInfoDto

}