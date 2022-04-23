package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("subscribers")
    var subscribers: Int = 0,
    @SerializedName("contributors")
    var contributors: Int = 0,
    @SerializedName("stars")
    var stars: Int = 0,
    @SerializedName("followers")
    var followers: Int = 0
)