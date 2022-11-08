package com.payment.myapplication.ui.components.molecules

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CustomAnimation(animation: Int, onAnimationFinish: () -> Unit = {}) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress }
    )
    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        onAnimationFinish()
    }
}