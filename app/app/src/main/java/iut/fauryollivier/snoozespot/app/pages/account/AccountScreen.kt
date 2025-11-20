package iut.fauryollivier.snoozespot.app.pages.account

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.AnonymousOnly
import iut.fauryollivier.snoozespot.app.components.AuthOnly
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.app.destinations.LoginScreenDestination

@Destination
@Composable
fun AccountScreen(navigator: DestinationsNavigator, scaffoldController: ScaffoldController, modifier: Modifier = Modifier, vm: AccountViewModel = viewModel()) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    val context = LocalContext.current

    AnonymousOnly {
        Button(
            onClick = { navigator.navigate(LoginScreenDestination) }
        ) {
            Text(stringResource(R.string.login))
        }
    }

    AuthOnly {
        Button(
            onClick = { vm.logout(context) },
        ) {
            Text(stringResource(R.string.logout))
        }
    }
    // TODO
}