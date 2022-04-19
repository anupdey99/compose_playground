package com.anupdey.app.compose_playground.data.mapper

import com.anupdey.app.compose_playground.data.local.CompanyListingEntity
import com.anupdey.app.compose_playground.data.remote.dto.CompanyInfoDto
import com.anupdey.app.compose_playground.domain.model.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}
