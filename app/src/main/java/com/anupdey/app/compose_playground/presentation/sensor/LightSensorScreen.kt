package com.anupdey.app.compose_playground.presentation.sensor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LightSensorScreen(
    navigator: DestinationsNavigator,
    viewModel: SensorViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val isDark = state < 60f
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDark) Color.DarkGray else Color.White
            )
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isDark) {
                    "Dark mode"
                } else {
                    "Light mode"
                }, color = if (isDark) Color.White else Color.DarkGray
            )
            Text(
                text = "Lux $state", color = if (isDark) Color.White else Color.DarkGray
            )
        }
    }
}