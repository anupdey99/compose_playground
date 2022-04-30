package com.anupdey.app.compose_playground.presentation.music_knob

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.roundToInt

@Destination
@ExperimentalComposeUiApi
@Composable
fun MusicControlScreen(
    navigator: DestinationsNavigator
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(30.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 20
            MusicKnob(modifier = Modifier.size(100.dp)) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )

        }
    }
}