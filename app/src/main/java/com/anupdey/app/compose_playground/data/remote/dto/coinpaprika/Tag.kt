package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("coin_counter")
    var coinCounter: Int = 0,
    @SerializedName("ico_counter")
    var icoCounter: Int = 0
)