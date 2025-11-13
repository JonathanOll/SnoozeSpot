package iut.fauryollivier.snoozespot.app.pages.account

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar

@Destination
@Composable
fun AccountScreen(navigator: DestinationsNavigator, scaffoldController: ScaffoldController, modifier: Modifier = Modifier) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    Image(
        painterResource(R.drawable.construction),
        contentDescription = "construction"
    )
    // TODO
}