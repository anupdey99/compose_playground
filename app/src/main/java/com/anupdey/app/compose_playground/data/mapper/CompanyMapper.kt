package com.anupdey.app.compose_playground.data.mapper

import com.anupdey.app.compose_playground.data.local.entities.stock.CompanyListingEntity
import com.anupdey.app.compose_playground.data.remote.dto.stock.CompanyInfoDto
import com.anupdey.app.compose_playground.domain.model.stock.CompanyInfo
import com.anupdey.app.compose_playground.domain.model.stock.CompanyListing

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
