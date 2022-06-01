package com.anupdey.app.compose_playground.presentation.stock_market.company_info

import com.anupdey.app.compose_playground.domain.model.stock.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

