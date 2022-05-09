package com.anupdey.app.compose_playground.presentation.sensor

abstract class MeasurableSensor(
    protected val sensorType: Int
) {

    abstract val doesSensorExist: Boolean
    abstract fun startListening()
    abstract fun stopListening()

    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    fun setOnSensorValuesChangedListener(lister: (List<Float>) -> Unit) {
        onSensorValuesChanged = lister
    }
}