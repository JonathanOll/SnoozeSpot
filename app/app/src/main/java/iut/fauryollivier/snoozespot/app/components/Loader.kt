package iut.fauryollivier.snoozespot.app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import iut.fauryollivier.snoozespot.R

@Composable
fun Loader(modifier: Modifier = Modifier, speed: Float = 1f, size: Float = 0.3f) {
    val anim by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = anim,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = true,
            modifier = Modifier.fillMaxWidth(size),
            speed = speed
        )
    }

}