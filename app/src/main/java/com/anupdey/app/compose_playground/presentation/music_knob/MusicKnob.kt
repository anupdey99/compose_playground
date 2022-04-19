package com.anupdey.app.compose_playground.presentation.music_knob

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import com.anupdey.app.compose_playground.R
import kotlin.math.PI
import kotlin.math.atan2

@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChanged: (Float) -> Unit
) {
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val bounds = it.boundsInWindow()
                centerX = bounds.size.width / 2f
                centerY = bounds.size.height / 2f
            }
            .pointerInteropFilter { motionEvent ->

                touchX = motionEvent.x
                touchY = motionEvent.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {

                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else angle
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - (2 * limitingAngle))
                            onValueChanged(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )

}