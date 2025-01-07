package com.example.pushtest.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun MyTimeApp() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val yPerMinute = with(LocalDensity.current) { screenHeight.toPx() / 1440f }

    val animatedYPosition = remember { Animatable(0f) }

    val currentTimeState = remember { mutableStateOf(Calendar.getInstance()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTimeState.value = Calendar.getInstance()
            delay(1000)
        }
    }

    val currentHour = currentTimeState.value.get(Calendar.HOUR_OF_DAY)
    val currentMinute = currentTimeState.value.get(Calendar.MINUTE)
    val totalMinutes = currentHour * 60 + currentMinute
    val targetYPosition = yPerMinute * totalMinutes

    LaunchedEffect(targetYPosition) {
        animatedYPosition.animateTo(
            targetYPosition,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "${currentHour}:${String.format("%02d", currentMinute)}",
            modifier = Modifier
                .offset(y = animatedYPosition.value.dp)
                .align(Alignment.TopStart),
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}
