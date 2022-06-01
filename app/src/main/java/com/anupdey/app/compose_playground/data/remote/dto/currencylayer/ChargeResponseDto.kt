package com.anupdey.app.compose_playground.data.remote.dto.currencylayer


import com.google.gson.annotations.SerializedName

data class ChargeResponseDto(
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("timestamp")
    var timestamp: Long = 0,
    @SerializedName("source")
    var source: String? = "",
    @SerializedName("quotes")
    var quotes: Map<String, Double> = hashMapOf()
)