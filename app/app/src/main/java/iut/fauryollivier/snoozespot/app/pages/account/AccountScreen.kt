package iut.fauryollivier.snoozespot.app.pages.account

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.AnonymousOnly
import iut.fauryollivier.snoozespot.app.components.AuthOnly
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.app.components.HorizontalLine
import iut.fauryollivier.snoozespot.app.destinations.LoginScreenDestination
import iut.fauryollivier.snoozespot.app.pages.account.components.UserProfileCard
import iut.fauryollivier.snoozespot.datastore.LocalStorage

@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    modifier: Modifier = Modifier,
    vm: AccountViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null)
            vm.changeProfilePic(context, uri)
    }

    val user by LocalStorage(LocalContext.current).getUser.collectAsState(null)

    AnonymousOnly {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { navigator.navigate(LoginScreenDestination) }
            ) {
                Icon(
                    Icons.Default.Login,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(stringResource(R.string.login), color = Color.White)
            }
        }
    }

    AuthOnly {
        if (user != null)
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                UserProfileCard(
                    user!!
                ) {
                    launcher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
                HorizontalLine()
                Button(onClick = {vm.logout(context)}) {
                    Text(stringResource(R.string.logout), color = Color.White)
                }
            }
    }
}