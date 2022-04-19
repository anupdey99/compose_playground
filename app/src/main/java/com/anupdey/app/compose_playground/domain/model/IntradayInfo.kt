package com.anupdey.app.compose_playground.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val close: Double
)
