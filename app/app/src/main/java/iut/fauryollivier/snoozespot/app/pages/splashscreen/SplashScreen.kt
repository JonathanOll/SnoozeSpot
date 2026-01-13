package iut.fauryollivier.snoozespot.app.pages.splashscreen

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.BuildConfig
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedScreenDestination
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    vm: SplashScreenViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { }
        scaffoldController.showBottomBar.value = false
    }

    val context: Context = LocalContext.current

    val anim by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_screen))
    val loaded by vm.loaded.collectAsState()
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 150f
        )
    )

    LaunchedEffect(true) {
        vm.load(LocalStorage(context))
        delay(100)
        startAnimation = true
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen_gradient),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.icon_shadow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.7f)
                .scale(scale)
        )

        LottieAnimation(
            composition = anim,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = true,
            modifier = Modifier
                .fillMaxWidth(1f)
                .scale(scale)
        )

        if (BuildConfig.FLAVOR.contains("dev") || BuildConfig.FLAVOR.contains("beta"))
            Text(
                BuildConfig.VERSION_NAME,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
    }

    LaunchedEffect(loaded) {
        if (loaded)
            navigator.navigate(FeedScreenDestination)
    }
}