package com.anupdey.app.compose_playground.presentation.company_info

import com.anupdey.app.compose_playground.domain.model.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

