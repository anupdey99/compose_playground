package com.anupdey.app.compose_playground.di

import android.app.Application
import androidx.room.Room
import com.anupdey.app.compose_playground.data.local.AppDatabase
import com.anupdey.app.compose_playground.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppConstant.DATABASE_NAME
        ).build()
    }
}