package com.anupdey.app.compose_playground.presentation.circular_progress_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    radius: Dp = 50.dp,
    strokeWidth: Dp = 8.dp
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            delayMillis = 0,
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .size(radius * 2f)
            .padding(5.dp), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360f * curPercentage.value,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(text = (number * curPercentage.value).toInt().toString(), color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }

}