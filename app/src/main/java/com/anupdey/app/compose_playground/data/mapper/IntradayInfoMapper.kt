package com.anupdey.app.compose_playground.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.anupdey.app.compose_playground.data.remote.dto.stock.IntradayInfoDto
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}