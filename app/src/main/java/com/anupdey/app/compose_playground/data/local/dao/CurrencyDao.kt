package com.anupdey.app.compose_playground.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anupdey.app.compose_playground.data.local.entities.currencylayer.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CurrencyEntity>)

    @Query("SELECT * FROM currency")
    fun fetchAllFlow(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency")
    fun fetchAllLiveData(): LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM currency")
    suspend fun fetchAll(): List<CurrencyEntity>

    @Query("SELECT * FROM currency ORDER BY createdAt DESC LIMIT 1")
    suspend fun fetchLatest(): CurrencyEntity

    @Query("DELETE FROM currency")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteTableAndInsert(list: List<CurrencyEntity>) {
        deleteAll()
        insertAll(list)
    }

}