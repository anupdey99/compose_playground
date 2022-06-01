package com.anupdey.app.compose_playground.data.csv

import android.os.Build
import androidx.annotation.RequiresApi
import com.anupdey.app.compose_playground.data.mapper.toIntradayInfo
import com.anupdey.app.compose_playground.data.remote.dto.stock.IntradayInfoDto
import com.anupdey.app.compose_playground.domain.model.stock.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor(): CSVParser<IntradayInfo> {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        var recentDay = 0
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(timestamp, close.toDouble())
                    dto.toIntradayInfo()
                }.sortedByDescending {
                    it.date.dayOfMonth
                }.also {
                    recentDay = it.firstOrNull()?.date?.dayOfMonth ?: 0
                }
                .filter {
                    //it.date.dayOfMonth == LocalDate.now().minusDays(1).dayOfMonth
                    it.date.dayOfMonth == recentDay
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }

}