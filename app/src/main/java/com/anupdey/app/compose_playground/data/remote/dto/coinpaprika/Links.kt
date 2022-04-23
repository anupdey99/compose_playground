package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("explorer")
    var explorer: List<String>? = listOf(),
    @SerializedName("facebook")
    var facebook: List<String>? = listOf(),
    @SerializedName("reddit")
    var reddit: List<String>? = listOf(),
    @SerializedName("source_code")
    var sourceCode: List<String>? = listOf(),
    @SerializedName("website")
    var website: List<String>? = listOf(),
    @SerializedName("youtube")
    var youtube: List<String>? = listOf()
)