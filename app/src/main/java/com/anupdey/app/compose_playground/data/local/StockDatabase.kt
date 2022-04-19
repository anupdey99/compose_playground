package com.anupdey.app.compose_playground.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anupdey.app.compose_playground.util.AppConstant

@Database(
    entities = [
        CompanyListingEntity::class
    ],
    version = AppConstant.DATABASE_VERSION
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}