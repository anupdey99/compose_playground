package com.anupdey.app.compose_playground.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anupdey.app.compose_playground.data.local.dao.CurrencyDao
import com.anupdey.app.compose_playground.data.local.dao.StockDao
import com.anupdey.app.compose_playground.data.local.entities.currencylayer.CurrencyEntity
import com.anupdey.app.compose_playground.data.local.entities.stock.CompanyListingEntity
import com.anupdey.app.compose_playground.util.AppConstant

@Database(
    entities = [
        CompanyListingEntity::class,
        CurrencyEntity::class
    ],
    version = AppConstant.DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {
    abstract val stockDao: StockDao
    abstract val currencyDao: CurrencyDao
}