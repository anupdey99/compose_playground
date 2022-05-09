package com.anupdey.app.compose_playground.presentation.sensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class LightSensor(
    context: Context
) : AndroidSensor(
    context,
    PackageManager.FEATURE_SENSOR_LIGHT,
    Sensor.TYPE_LIGHT
)