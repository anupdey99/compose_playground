package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class Whitepaper(
    @SerializedName("link")
    var link: String? = "",
    @SerializedName("thumbnail")
    var thumbnail: String? = ""
)