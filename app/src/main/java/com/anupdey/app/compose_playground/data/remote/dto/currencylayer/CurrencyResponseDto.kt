package com.anupdey.app.compose_playground.data.remote.dto.currencylayer


import com.google.gson.annotations.SerializedName

data class CurrencyResponseDto(
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("currencies")
    var currencies: Map<String, String> = hashMapOf()
)