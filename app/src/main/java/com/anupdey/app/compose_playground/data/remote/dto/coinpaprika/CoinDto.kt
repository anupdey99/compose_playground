package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("symbol")
    var symbol: String? = "",
    @SerializedName("rank")
    var rank: Int = 0,
    @SerializedName("is_new")
    var isNew: Boolean = false,
    @SerializedName("is_active")
    var isActive: Boolean = false,
    @SerializedName("type")
    var type: String? = ""
)