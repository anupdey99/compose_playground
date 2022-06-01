package com.anupdey.app.compose_playground.di

import android.app.Application
import com.anupdey.app.compose_playground.presentation.sensor.LightSensor
import com.anupdey.app.compose_playground.presentation.sensor.MeasurableSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLightSensor(app: Application): MeasurableSensor {
        return LightSensor(app)
    }
}