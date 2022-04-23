package com.anupdey.app.compose_playground.data.remote.dto.coinpaprika


import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
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
    var type: String? = "",
    @SerializedName("tags")
    var tags: List<Tag>? = listOf(),
    @SerializedName("team")
    var team: List<TeamMember>? = listOf(),
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("open_source")
    var openSource: Boolean = false,
    @SerializedName("started_at")
    var startedAt: String? = "",
    @SerializedName("development_status")
    var developmentStatus: String? = "",
    @SerializedName("hardware_wallet")
    var hardwareWallet: Boolean = false,
    @SerializedName("proof_type")
    var proofType: String? = "",
    @SerializedName("org_structure")
    var orgStructure: String? = "",
    @SerializedName("hash_algorithm")
    var hashAlgorithm: String? = "",
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("links_extended")
    var linksExtended: List<LinksExtended>? = listOf(),
    @SerializedName("whitepaper")
    var whitepaper: Whitepaper? = Whitepaper(),
    @SerializedName("first_data_at")
    var firstDataAt: String? = "",
    @SerializedName("last_data_at")
    var lastDataAt: String? = ""
)