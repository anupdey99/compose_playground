package com.anupdey.app.compose_playground.presentation.sensor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val lightSensor: MeasurableSensor
): ViewModel() {

    var state by mutableStateOf(0f)

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            state = values.first()
        }
    }

    override fun onCleared() {
        lightSensor.stopListening()
        super.onCleared()
    }
}