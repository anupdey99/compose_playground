package com.anupdey.app.compose_playground.domain.model.coinpaprika

import com.anupdey.app.compose_playground.data.remote.dto.coinpaprika.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
