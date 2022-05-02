package com.anupdey.app.compose_playground.presentation.stock_market.company_listings

import com.anupdey.app.compose_playground.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
