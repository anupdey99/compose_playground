package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class LinksExtended(
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("stats")
    var stats: Stats? = Stats()
)